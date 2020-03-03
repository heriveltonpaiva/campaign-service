package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.queue.AMQPProducer;
import br.com.hpaiva.campaignservice.queue.Notification;
import br.com.hpaiva.campaignservice.team.TeamService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository repository;

    private TeamService teamService;

    private AMQPProducer producer;

    @Override
    public CampaignDTO createCampaign(CampaignRequest request) throws NotFoundException {

        final var team = teamService.findById(request.getIdHeartTeam()).orElseThrow(() ->
                new NotFoundException("Não há time cadastrado para o idHeartTeam=" + request.getIdHeartTeam()));

        log.info("m=createCampaign status=initial team=" + team);

        final var campaign = Campaign.builder()
                .name(request.getName())
                .team(team)
                .startEffectiveDate(request.getStartEffectiveDate())
                .endEffectiveDate(request.getEndEffectiveDate())
                .createAt(LocalDateTime.now())
                .build();

        final var allActivesCampaigns = repository.findAllActivesByIdHeartTeam(team.getId());
        final var updateCampaigns = validateConflictingPeriods(allActivesCampaigns, campaign.getEndEffectiveDate());

        updateCampaigns.add(campaign);
        repository.saveAll(updateCampaigns);

        log.info("m=createCampaign status=final team=" + team + " campaign=" + campaign);

        return toDto(campaign);
    }

    @Override
    public CampaignDTO updateCampaign(Long campaignId, CampaignRequest request) throws NotFoundException {
        var campaign = repository.findById(campaignId).get();

        final var team = teamService.findById(request.getIdHeartTeam()).orElseThrow(() ->
                new NotFoundException("Não há time cadastrado para o idHeartTeam=" + request.getIdHeartTeam()));
        log.info("m=update status=initial team=" + team);

        campaign.setTeam(team);
        campaign.setName(request.getName());
        campaign.setStartEffectiveDate(request.getStartEffectiveDate());
        campaign.setEndEffectiveDate(request.getEndEffectiveDate());
        campaign.setUpdateAt(LocalDateTime.now());

        final var allActivesCampaigns = repository.findAllActivesByIdHeartTeam(team.getId());
        final var updateCampaigns = validateConflictingPeriods(allActivesCampaigns, campaign.getEndEffectiveDate());

        updateCampaigns.add(campaign);
        repository.saveAll(updateCampaigns);

        log.info("m=createCampaign status=final team=" + team + " campaign=" + campaign);

        updateCampaigns.forEach(campaignUpdated -> {
            publishQueue(toDto(campaignUpdated));
        });

        return toDto(campaign);
    }

    @Override
    public void delete(Long campaignRequestId) {
        repository.deleteById(campaignRequestId);
    }

    @Override
    public List<CampaignDTO> findCampaignsByIdHeartTeam(final Long idHeartTeam) {
        final var list = repository.findAllActivesByIdHeartTeam(idHeartTeam);
        log.info("m=findAllActives listsize=" + list.size());
        return list.stream().map(campaign -> toDto(campaign)).collect(Collectors.toList());
    }

    private void publishQueue(CampaignDTO campaignDTO) {
        var notification = new Notification();
        notification.setData(campaignDTO);
        producer.sendMessage(notification);
    }

    public List<Campaign> validateConflictingPeriods(List<Campaign> campaigns, LocalDate endDate) {

        final var updatedCampaigns = campaigns.stream().filter(campaign ->
                campaign.getEndEffectiveDate().equals(endDate) && !campaign.isConflicted()).findFirst()
                .map(campaign -> {
                    campaign.setEndEffectiveDate(campaign.getEndEffectiveDate().plusDays(1));
                    campaign.setUpdateAt(LocalDateTime.now());
                    campaign.setConflicted(Boolean.TRUE);
                    return campaign;
                });

        if (!updatedCampaigns.isEmpty())
            return validateConflictingPeriods(campaigns, endDate.plusDays(1));

        return campaigns;
    }

    private CampaignDTO toDto(Campaign campaign) {
        final var dto = CampaignDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .idHeartTeam(campaign.getTeam().getId())
                .startEffectiveDate(campaign.getStartEffectiveDate())
                .endEffectiveDate(campaign.getEndEffectiveDate())
                .build();
        return dto;
    }
}

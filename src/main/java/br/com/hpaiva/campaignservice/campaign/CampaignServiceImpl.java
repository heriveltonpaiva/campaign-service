package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.queue.AMQPProducer;
import br.com.hpaiva.campaignservice.queue.Notification;
import br.com.hpaiva.campaignservice.team.TeamService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    public Campaign save(CampaignDTO campaignDTO) {

        final var team = teamService.findById(campaignDTO.getIdHeartTeam());
        log.info("m=save status=initial team=" + team);
        final var campaign = Campaign.builder()
                .name(campaignDTO.getName())
                .team(team.get())
                .startEffectiveDate(campaignDTO.getStartEffectiveDate())
                .endEffectiveDate(campaignDTO.getEndEffectiveDate())
                .build();
        //TODO implement campaign bussiness rules before save
        log.info("m=save status=final team=" + team + " campaign=" + campaign);
        return repository.save(campaign);
    }

    @Override
    public Campaign update(Long campaignId, CampaignDTO campaignDTO) {
        var campaign = repository.findById(campaignId);
        //TODO
        campaign.get().setTeam(null);
        campaign.get().setName(campaignDTO.getName());
        campaign.get().setStartEffectiveDate(campaignDTO.getStartEffectiveDate());
        campaign.get().setEndEffectiveDate(campaignDTO.getEndEffectiveDate());

        //TODO transform toDto and validate equals before post on queue
        //TODO if campaign is equals then notify another services
        publishQueue(campaign.get());

        return repository.save(campaign.get());
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

    private void publishQueue(Campaign campaign) {
        var notification = new Notification();
        notification.setData(campaign);
        producer.sendMessage(notification);
    }

    public List<Campaign> changeCampaignDates(List<Campaign> campaigns) {

        return null;
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

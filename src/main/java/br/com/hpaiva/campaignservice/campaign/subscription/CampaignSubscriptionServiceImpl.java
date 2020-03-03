package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.campaign.CampaignDTO;
import br.com.hpaiva.campaignservice.campaign.CampaignService;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import br.com.hpaiva.campaignservice.team.Team;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignSubscriptionServiceImpl implements CampaignSubscriptionService {

    private CampaignSubscriptionRepository repository;

    private CampaignService campaignService;

    @Override
    public void subscription(ClubSupporterDTO dto) throws NotFoundException {

        final var campaignsSaved = campaignService.findCampaignsByIdHeartTeam(dto.getIdHeartTeam());

        if(campaignsSaved.isEmpty())
            throw new NotFoundException("Não há campanhas cadastradas para o time idHeartTeam="+dto.getIdHeartTeam());

        campaignsSaved.forEach(campaign -> {
            var newSubscription = CampaignSubscription.builder().clubSupporter(toEntity(dto)).campaign(toEntity(campaign)).build();
            repository.save(newSubscription);
            log.info("m=subscription status=saved campaignId={}", newSubscription.getId());
        });

    }

    @Override
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter) {
        var list = repository.findCampaignSubscriptionsByClubSupporterActive(idClubSupporter);
        return list.stream().map(c -> toDto(c)).collect(Collectors.toList());
    }

    private CampaignSubscriptionDTO toDto(final CampaignSubscription campaignSubscription) {
        return CampaignSubscriptionDTO.builder()
                .id(campaignSubscription.getId())
                .campaignDTO(toDto(campaignSubscription.getCampaign()))
                .clubSupporterDTO(toDto(campaignSubscription.getClubSupporter()))
                .build();
    }

    private ClubSupporterDTO toDto(ClubSupporter clubSupporter) {
        return ClubSupporterDTO.builder()
                .id(clubSupporter.getId())
                .name(clubSupporter.getName())
                .email(clubSupporter.getEmail())
                .birthDate(clubSupporter.getBirthDate())
                .active(clubSupporter.isActive())
                .idHeartTeam(clubSupporter.getTeam().getId())
                .build();
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

    private ClubSupporter toEntity(ClubSupporterDTO dto) {
        return ClubSupporter.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .team(Team.builder().id(dto.getIdHeartTeam()).build())
                .active(Boolean.TRUE)
                .build();
    }

    private Campaign toEntity(CampaignDTO dto) {
        return Campaign.builder()
                .id(dto.getId())
                .startEffectiveDate(dto.getStartEffectiveDate())
                .endEffectiveDate(dto.getEndEffectiveDate())
                .name(dto.getName())
                .team(Team.builder().id(dto.getIdHeartTeam()).build())
                .build();
    }
}

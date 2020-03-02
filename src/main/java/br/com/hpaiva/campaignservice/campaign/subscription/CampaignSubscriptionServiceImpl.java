package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.campaign.CampaignDTO;
import br.com.hpaiva.campaignservice.campaign.CampaignService;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CampaignSubscriptionServiceImpl implements CampaignSubscriptionService{

    private CampaignSubscriptionRepository repository;

    private CampaignService campaignService;

    @Override
    public void subscription(ClubSupporterDTO clubSupporterDTO) {

        campaignService.findCampaignsByIdHeartTeam(clubSupporterDTO.getIdHeartTeam()).forEach(campaign -> {
            var newSubscription = CampaignSubscription.builder().clubSupporter(null).campaign(null).team(null).build();
            repository.save(newSubscription);
        });
    }

    @Override
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter) {
        var list = repository.findCampaignSubscriptionsByClubSupporterActive(idClubSupporter);
        return list.stream().map(c -> toDto(c)).collect(Collectors.toList());
    }

    private CampaignSubscriptionDTO toDto(final CampaignSubscription campaignSubscription){
        return CampaignSubscriptionDTO.builder()
                .idHearTeam(campaignSubscription.getTeam().getId())
                .campaignDTO(toDto(campaignSubscription.getCampaign()))
                .clubSupporterDTO(toDto(campaignSubscription.getClubSupporter()))
                .build();
    }

    private ClubSupporterDTO toDto(ClubSupporter clubSupporter){
        return ClubSupporterDTO.builder()
                .name(clubSupporter.getName())
                .email(clubSupporter.getEmail())
                .birthDate(clubSupporter.getBirthDate())
                .active(clubSupporter.isActive())
                .idHeartTeam(clubSupporter.getTeam().getId())
                .build();
    }

    private CampaignDTO toDto(Campaign campaign) {
        final var dto = CampaignDTO.builder()
                .name(campaign.getName())
                .idHeartTeam(campaign.getTeam().getId())
                .startEffectiveDate(campaign.getStartEffectiveDate())
                .endEffectiveDate(campaign.getEndEffectiveDate())
                .build();
        return dto;
    }
}

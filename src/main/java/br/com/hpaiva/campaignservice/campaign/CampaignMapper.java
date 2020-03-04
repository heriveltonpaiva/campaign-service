package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.campaign.subscription.CampaignSubscription;
import br.com.hpaiva.campaignservice.campaign.subscription.CampaignSubscriptionDTO;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import br.com.hpaiva.campaignservice.team.Team;
import org.springframework.stereotype.Component;

@Component
public class CampaignMapper {

    public static CampaignDTO toDto(Campaign campaign) {
        final var dto = CampaignDTO.builder()
                .id(campaign.getId())
                .name(campaign.getName())
                .idHeartTeam(campaign.getTeam().getId())
                .startEffectiveDate(campaign.getStartEffectiveDate())
                .endEffectiveDate(campaign.getEndEffectiveDate())
                .build();
        return dto;
    }

    public static CampaignSubscriptionDTO toDto(final CampaignSubscription campaignSubscription) {
        return CampaignSubscriptionDTO.builder()
                .id(campaignSubscription.getId())
                .campaignDTO(toDto(campaignSubscription.getCampaign()))
                .clubSupporterDTO(toDto(campaignSubscription.getClubSupporter()))
                .build();
    }

    public static ClubSupporterDTO toDto(ClubSupporter clubSupporter) {
        return ClubSupporterDTO.builder()
                .id(clubSupporter.getId())
                .name(clubSupporter.getName())
                .email(clubSupporter.getEmail())
                .birthDate(clubSupporter.getBirthDate())
                .active(clubSupporter.isActive())
                .idHeartTeam(clubSupporter.getTeam().getId())
                .build();
    }

    public static ClubSupporter toEntity(ClubSupporterDTO dto) {
        return ClubSupporter.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .birthDate(dto.getBirthDate())
                .team(Team.builder().id(dto.getIdHeartTeam()).build())
                .active(Boolean.TRUE)
                .build();
    }

    public static Campaign toEntity(CampaignDTO dto) {
        return Campaign.builder()
                .id(dto.getId())
                .startEffectiveDate(dto.getStartEffectiveDate())
                .endEffectiveDate(dto.getEndEffectiveDate())
                .name(dto.getName())
                .team(Team.builder().id(dto.getIdHeartTeam()).build())
                .build();
    }

}

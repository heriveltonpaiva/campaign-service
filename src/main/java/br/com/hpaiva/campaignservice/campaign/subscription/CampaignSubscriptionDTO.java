package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.CampaignDTO;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignSubscriptionDTO {

    private Long id;

    private CampaignDTO campaignDTO;

    private Long idHearTeam;

    private ClubSupporterDTO clubSupporterDTO;

}

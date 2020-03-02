package br.com.hpaiva.campaignservice.campaign.subscription;


import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;

import java.util.List;

public interface CampaignSubscriptionService {

    void subscription(ClubSupporterDTO clubSupporterDTO);

    List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter);

}

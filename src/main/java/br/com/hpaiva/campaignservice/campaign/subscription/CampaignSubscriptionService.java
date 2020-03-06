package br.com.hpaiva.campaignservice.campaign.subscription;


import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import javassist.NotFoundException;

import java.util.List;

public interface CampaignSubscriptionService {

    void subscription(ClubSupporterDTO clubSupporterDTO) throws NotFoundException;

    List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter) throws NotFoundException;

}

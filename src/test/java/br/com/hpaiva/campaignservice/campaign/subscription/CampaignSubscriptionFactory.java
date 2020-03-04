package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.CampaignFactory;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterFactory;
import org.hamcrest.Factory;

public class CampaignSubscriptionFactory {

    @Factory
    public static CampaignSubscription campaignSubscription() {
        return CampaignSubscription.builder()
                .campaign(CampaignFactory.singleCampaignList().get(0))
                .clubSupporter(ClubSupporterFactory.clubSupporter())
                .build();
    }

}

package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.CampaignService;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CampaignSubscriptionServiceImpl implements CampaignSubscriptionService{

    private CampaignSubscriptionRepository repository;

    private CampaignService campaignService;

    @Override
    public void subscription(ClubSupporter clubSupporter) {

        campaignService.findAll().forEach(campaign -> {
        var newSubscription = CampaignSubscription.builder().clubSupporter(clubSupporter).campaign(campaign).team(clubSupporter.getTeam()).build();
        repository.save(newSubscription);
        });
    }
}

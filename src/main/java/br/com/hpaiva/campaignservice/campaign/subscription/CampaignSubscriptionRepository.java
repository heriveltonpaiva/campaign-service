package br.com.hpaiva.campaignservice.campaign.subscription;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignSubscriptionRepository extends CrudRepository<CampaignSubscription, Long> {
}

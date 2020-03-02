package br.com.hpaiva.campaignservice.campaign.subscription;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignSubscriptionRepository extends CrudRepository<CampaignSubscription, Long> {

    @Query("select c from CampaignSubscription c where c.clubSupporter.id = :idClubSupporter " +
            " and CURRENT_DATE between c.campaign.startEffectiveDate and c.campaign.endEffectiveDate and c.clubSupporter.active = true")
    List<CampaignSubscription> findCampaignSubscriptionsByClubSupporterActive(@Param("idClubSupporter") Long idClubSupporter);
}

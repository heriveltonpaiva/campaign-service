package br.com.hpaiva.campaignservice.campaign;

import java.util.List;

public interface CampaignService {

    Campaign save(CampaignRequest campaignRequest);

    Campaign update(Long campaignId, CampaignRequest campaignRequest);

    void delete(Long campaignId);

    List<Campaign> findAll();

    List<Campaign> changeCampaignDates(List<Campaign> campaigns);
}

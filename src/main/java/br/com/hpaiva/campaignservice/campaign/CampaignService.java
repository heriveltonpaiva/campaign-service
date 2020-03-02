package br.com.hpaiva.campaignservice.campaign;

import java.util.List;

public interface CampaignService {

    Campaign save(CampaignDTO campaignDTO);

    Campaign update(Long campaignId, CampaignDTO campaignDTO);

    void delete(Long campaignId);

    List<CampaignDTO> findCampaignsByIdHeartTeam(final Long idHeartTeam);

    List<Campaign> changeCampaignDates(List<Campaign> campaigns);
}

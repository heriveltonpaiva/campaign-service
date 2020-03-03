package br.com.hpaiva.campaignservice.campaign;

import javassist.NotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface CampaignService {

    CampaignDTO createCampaign(CampaignRequest campaignRequest) throws NotFoundException;

    CampaignDTO updateCampaign(Long campaignId, CampaignRequest campaignRequest) throws NotFoundException;

    void delete(Long campaignId);

    List<CampaignDTO> findCampaignsByIdHeartTeam(final Long idHeartTeam);

    List<Campaign> validateConflictingPeriods(List<Campaign> campaigns, LocalDate localDate);
}

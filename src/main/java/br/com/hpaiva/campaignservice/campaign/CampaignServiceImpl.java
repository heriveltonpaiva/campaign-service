package br.com.hpaiva.campaignservice.campaign;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    public CampaignRepository repository;

    @Override
    public Campaign save(CampaignRequest campaignRequest) {
        final var campaign = Campaign.builder().
                name(campaignRequest.getName()).
                idHeartTeam(campaignRequest.getIdHeartTeam()).
                startEffectiveDate(campaignRequest.getStartEffectiveDate()).
                endEffectiveDate(campaignRequest.getEndEffectiveDate()).
                build();
        return repository.save(campaign);
    }
}

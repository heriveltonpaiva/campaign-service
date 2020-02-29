package br.com.hpaiva.campaignservice.campaign;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public Campaign update(Long campaignId, CampaignRequest campaignRequest) {
        var campaign = repository.findById(campaignId);

        campaign.get().setIdHeartTeam(campaignRequest.getIdHeartTeam());
        campaign.get().setName(campaignRequest.getName());
        campaign.get().setStartEffectiveDate(campaignRequest.getStartEffectiveDate());
        campaign.get().setEndEffectiveDate(campaignRequest.getEndEffectiveDate());

        return repository.save(campaign.get());
    }

    @Override
    public void delete(Long campaignRequestId) {
        repository.deleteById(campaignRequestId);
    }

    @Override
    public List<Campaign> findAll() {
        return Lists.newArrayList(repository.findAll());
    }
}

package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.queue.AMQPProducer;
import br.com.hpaiva.campaignservice.queue.Notification;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository repository;

    private AMQPProducer producer;

    @Override
    public Campaign save(CampaignRequest campaignRequest) {
        final var campaign = Campaign.builder().
                name(campaignRequest.getName()).
                idHeartTeam(campaignRequest.getIdHeartTeam()).
                startEffectiveDate(campaignRequest.getStartEffectiveDate()).
                endEffectiveDate(campaignRequest.getEndEffectiveDate()).
                build();
        //TODO implement campaign bussiness rules before save
        return repository.save(campaign);
    }

    @Override
    public Campaign update(Long campaignId, CampaignRequest campaignRequest) {
        var campaign = repository.findById(campaignId);

        campaign.get().setIdHeartTeam(campaignRequest.getIdHeartTeam());
        campaign.get().setName(campaignRequest.getName());
        campaign.get().setStartEffectiveDate(campaignRequest.getStartEffectiveDate());
        campaign.get().setEndEffectiveDate(campaignRequest.getEndEffectiveDate());

        //TODO transform toDto and validate equals before post on queue
        //TODO if campaign is equals then notify another services
        publishQueue(campaign.get());

        return repository.save(campaign.get());
    }

    @Override
    public void delete(Long campaignRequestId) {
        repository.deleteById(campaignRequestId);
    }

    @Override
    public List<Campaign> findAll() {
        //TODO return only find compaign actives date
        return Lists.newArrayList(repository.findAll());
    }

    private void publishQueue(Campaign campaign){
        var notification = new Notification();
        notification.setData(campaign);
        producer.sendMessage(notification);
    }
}

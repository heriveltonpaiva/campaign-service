package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.queue.AMQPProducer;
import br.com.hpaiva.campaignservice.queue.Notification;
import br.com.hpaiva.campaignservice.team.TeamService;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository repository;

    private TeamService teamService;

    private AMQPProducer producer;

    @Override
    public Campaign save(CampaignRequest campaignRequest) {

        final var  team = teamService.findById(campaignRequest.getIdHeartTeam());
        log.info("m=save status=initial team="+team);
            final var campaign = Campaign.builder().
                    name(campaignRequest.getName()).
                    team(team.get()).
                    startEffectiveDate(campaignRequest.getStartEffectiveDate()).
                    endEffectiveDate(campaignRequest.getEndEffectiveDate()).
                    build();
            //TODO implement campaign bussiness rules before save
        log.info("m=save status=final team="+team+" campaign="+campaign);
        return repository.save(campaign);
    }

    @Override
    public Campaign update(Long campaignId, CampaignRequest campaignRequest) {
        var campaign = repository.findById(campaignId);

        campaign.get().setTeam(null);
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

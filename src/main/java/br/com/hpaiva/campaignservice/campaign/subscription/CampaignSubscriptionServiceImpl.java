package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.CampaignMapper;
import br.com.hpaiva.campaignservice.campaign.CampaignService;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class CampaignSubscriptionServiceImpl implements CampaignSubscriptionService {

    private CampaignSubscriptionRepository repository;
    private CampaignService campaignService;
    private CampaignMapper mapper;

    @Override
    public void subscription(ClubSupporterDTO dto) throws NotFoundException {
        final var campaignsSaved = campaignService.findCampaignsByIdHeartTeam(dto.getIdHeartTeam());

        if (campaignsSaved.isEmpty())
            throw new NotFoundException("Não há campanhas cadastradas para o time.");

        campaignsSaved.forEach(campaign -> {
            var newSubscription = CampaignSubscription.builder().clubSupporter(mapper.toEntity(dto)).campaign(mapper.toEntity(campaign)).build();
            repository.save(newSubscription);
            log.info("m=subscription status=saved campaignId={}", newSubscription.getId());
        });
    }

    @Override
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(Long idClubSupporter) throws NotFoundException {
        var list = repository.findCampaignSubscriptionsByClubSupporterActive(idClubSupporter);
        log.info("Não há campanhas associadas para esse sócio.");
        return list.stream().map(c -> mapper.toDto(c)).collect(Collectors.toList());
    }

}

package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/campaign-subscription")
@RestController
public class CampaignSubscriptionResource {

    private CampaignSubscriptionService service;

    @PostMapping
    @ApiOperation(value = "Associa o sócio torcedor as campanhas do seu time do coração")
    public void subscription(@RequestBody ClubSupporterDTO clubSupporterDTO) throws NotFoundException {
        service.subscription(clubSupporterDTO);
    }

    @GetMapping("/campaign-subscriptions/club-supporter/{idClubSupporter}")
    @ApiOperation(value = "Lista todas as campanhas associadas ao sócio torcedor")
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(@PathVariable Long idClubSupporter) throws NotFoundException {
        return service.findCampaignSubscriptionsByClubSupporter(idClubSupporter);
    }

}

package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.campaign.CampaignDTO;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterDTO;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RequestMapping("/campaign-subscription")
@RestController
public class CampaignSubscriptionResource {

    private CampaignSubscriptionService service;

    @PostMapping
    @ApiOperation(value = "Associa o sócio torcedor as campanhas do seu time do coração")
    public ResponseEntity<CampaignSubscriptionDTO> subscription(@RequestBody ClubSupporterDTO clubSupporterDTO){
        service.subscription(clubSupporterDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as campanhas associadas ao sócio torcedor")
    public List<CampaignSubscriptionDTO> findCampaignSubscriptionsByClubSupporter(final Long idClubSupporter){
        return service.findCampaignSubscriptionsByClubSupporter(idClubSupporter);
    }

}

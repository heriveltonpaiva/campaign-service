package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/campaign-subscription")
@RestController
public class CampaignSubscriptionResource {

    private CampaignSubscriptionService service;

    @PostMapping
    @ApiOperation(value = "Associa o sócio torcedor as campanhas do seu time do coração")
    public ResponseEntity<Campaign> subscription(@RequestBody ClubSupporter clubSupporter){
        service.subscription(clubSupporter);
        return ResponseEntity.ok().build();
    }

}

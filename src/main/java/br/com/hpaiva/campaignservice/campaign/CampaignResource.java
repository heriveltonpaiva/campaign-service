package br.com.hpaiva.campaignservice.campaign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/campaigns")
@Api(value = "Campanhas Vigentes")
public class CampaignResource {

    public CampaignService campaingService;

    @PostMapping
    @ApiOperation(
            value = "Salva uma nova campanha",
            notes = "Caso já exista essa mesma campanha a data de vigência sofrerá alterações.")
    public ResponseEntity<Campaign> save(@RequestBody CampaignRequest campaignRequest){
        campaingService.save(campaignRequest);
        return ResponseEntity.ok().build();
    }

}

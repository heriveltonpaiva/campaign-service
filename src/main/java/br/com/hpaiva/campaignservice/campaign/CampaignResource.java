package br.com.hpaiva.campaignservice.campaign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/campaigns")
@Api(value = "Campanhas Vigentes")
public class CampaignResource {

    public CampaignService campaignService;

    @PostMapping
    @ApiOperation(
            value = "Salva uma nova campanha",
            notes = "Caso já exista essa mesma campanha a data de vigência sofrerá alterações.")
    public ResponseEntity<Campaign> save(@RequestBody CampaignRequest campaignRequest){
        campaignService.save(campaignRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Campaign> update(@PathVariable Long id, @RequestBody CampaignRequest campaignRequest){
        var campaign = campaignService.update(id, campaignRequest);
        return ResponseEntity.ok(campaign);
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as campanhas vigentes cadastradas")
    public ResponseEntity<List<Campaign>> listAll(){
        return ResponseEntity.ok(campaignService.findAll());
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove uma campanha do sistema")
    public ResponseEntity delete(@PathVariable Long id){
        campaignService.delete(id);
        return ResponseEntity.ok().build();
    }

}

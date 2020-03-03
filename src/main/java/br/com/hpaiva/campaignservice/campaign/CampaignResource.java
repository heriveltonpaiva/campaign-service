package br.com.hpaiva.campaignservice.campaign;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/campaigns")
@Api(value = "Campanhas Vigentes")
public class CampaignResource {

    private CampaignService campaignService;

    @PostMapping
    @ApiOperation(
            value = "Salva uma nova campanha",
            notes = "Caso já exista essa mesma campanha a data de vigência sofrerá alterações.")
    public CampaignDTO save(@RequestBody CampaignRequest campaignRequest) throws NotFoundException {
        return campaignService.createCampaign(campaignRequest);
    }

    @PutMapping("/{idCampaign}")
    @ApiOperation("Atualiza os dados da campanha")
    public CampaignDTO update(@PathVariable Long idCampaign, @RequestBody CampaignRequest campaignRequest) throws NotFoundException {
        return campaignService.updateCampaign(idCampaign, campaignRequest);
    }

    @GetMapping
    @ApiOperation(value = "Lista todas as campanhas vigentes cadastradas")
    public List<CampaignDTO> findCampaignsByIdHeartTeam(Long idHeartTeam) {
        return campaignService.findCampaignsByIdHeartTeam(idHeartTeam);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove uma campanha do sistema")
    public ResponseEntity delete(@PathVariable Long id) {
        campaignService.delete(id);
        return ResponseEntity.ok().build();
    }

}

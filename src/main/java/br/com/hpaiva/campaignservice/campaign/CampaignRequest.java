package br.com.hpaiva.campaignservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {

    private String name;

    private LocalDate startEffectiveDate;

    private LocalDate endEffectiveDate;

    private Integer idHeartTeam;

}

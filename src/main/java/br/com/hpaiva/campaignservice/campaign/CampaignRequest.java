package br.com.hpaiva.campaignservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CampaignRequest {

    private String name;

    private LocalDateTime startEffectiveDate;

    private LocalDateTime endEffectiveDate;

    private Long idHeartTeam;

}

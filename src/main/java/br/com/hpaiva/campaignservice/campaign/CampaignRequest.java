package br.com.hpaiva.campaignservice.campaign;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class CampaignRequest {

    private String name;

    private LocalDate startEffectiveDate;

    private LocalDate endEffectiveDate;

    private Long idHeartTeam;

}

package br.com.hpaiva.campaignservice.campaign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CampaignDTO {

    private Long id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startEffectiveDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endEffectiveDate;

    private Long idHeartTeam;

}

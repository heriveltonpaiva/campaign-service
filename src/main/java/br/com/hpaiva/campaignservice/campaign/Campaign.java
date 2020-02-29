package br.com.hpaiva.campaignservice.campaign;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Campaign {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @Column(name = "start_effective_date")
    private LocalDateTime startEffectiveDate;

    @Column(name = "end_effective_date")
    private LocalDateTime endEffectiveDate;

    @Column(name = "id_heart_team")
    private Integer idHeartTeam;


}

package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
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

    @NotNull
    private String name;

    @NotNull
    @Column(name = "start_effective_date")
    private LocalDate startEffectiveDate;

    @NotNull
    @Column(name = "end_effective_date")
    private LocalDate endEffectiveDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_team")
    private Team team;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private LocalDateTime updateAt;

    @Transient
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private boolean conflicted;

}

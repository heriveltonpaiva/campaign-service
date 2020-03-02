package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;

import javax.persistence.*;
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

    private String name;

    @Column(name = "start_effective_date")
    private LocalDate startEffectiveDate;

    @Column(name = "end_effective_date")
    private LocalDate endEffectiveDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public LocalDateTime getCreateAt() {
        return createAt == null ? LocalDateTime.now() : createAt;
    }

    public LocalDateTime getUpdateAt() {
        return createAt != null ? updateAt : LocalDateTime.now();
    }

}

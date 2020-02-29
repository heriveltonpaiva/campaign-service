package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    @Column(name = "id_campaign")
    private Long id;

    private String name;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "start_effective_date")
    private LocalDateTime startEffectiveDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "end_effective_date")
    private LocalDateTime endEffectiveDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    public LocalDateTime getCreateAt(){
        return createAt == null ? LocalDateTime.now() : createAt;
    }

    public LocalDateTime getUpdateAt(){
        return createAt != null ? updateAt : LocalDateTime.now();
    }

}

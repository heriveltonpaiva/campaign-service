package br.com.hpaiva.campaignservice.clubsupporter;

import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "club_supporter")
public class ClubSupporter {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_team")
    @EqualsAndHashCode.Exclude
    private Team team;

    private boolean active;

}

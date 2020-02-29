package br.com.hpaiva.campaignservice.clubsupporter;

import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ClubSupporter {

    @Id
    @GeneratedValue
    @Column(name = "id_club_supporter")
    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    private boolean active;

}

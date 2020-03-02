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
@Table(name = "club_supporter")
public class ClubSupporter {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String email;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    private boolean active;

}

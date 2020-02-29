package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import br.com.hpaiva.campaignservice.team.Team;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CampaignSubscription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_campaign")
    private Campaign campaign;

    @ManyToOne
    @JoinColumn(name = "id_team")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "id_club_supporter")
    private ClubSupporter clubSupporter;

}

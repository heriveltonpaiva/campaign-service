package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.Campaign;
import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporter;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "campaign_subscription")
public class CampaignSubscription {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_campaign")
    private Campaign campaign;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_club_supporter")
    private ClubSupporter clubSupporter;

}

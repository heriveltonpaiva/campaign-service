package br.com.hpaiva.campaignservice.team;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "id_team")
    private Long id;

    private String name;

}

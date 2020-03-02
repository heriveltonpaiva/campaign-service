package br.com.hpaiva.campaignservice.clubsupporter;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ClubSupporterDTO {

    private Long id;

    private String name;

    private String email;

    private LocalDate birthDate;

    private Long idHeartTeam;

    private Boolean active;

}

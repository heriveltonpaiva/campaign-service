package br.com.hpaiva.campaignservice.clubsupporter;

import br.com.hpaiva.campaignservice.team.Team;
import org.hamcrest.Factory;

import java.time.LocalDate;

public class ClubSupporterFactory {

    public static final Long DEFAULT_ID = 1L;

    public static final Long DEFAULT_ID_TEAM = 1L;

    public static final String NAME = "HERIVELTON";

    public static final LocalDate BIRTH_DATE = LocalDate.of(1994, 03, 05);

    public static final String EMAIL = "hpaiva@teste.com";

    @Factory
    public static ClubSupporterDTO clubSupporterDTO() {
        return ClubSupporterDTO.builder().
                id(DEFAULT_ID).
                name(NAME).
                email(EMAIL).
                birthDate(BIRTH_DATE).
                idHeartTeam(DEFAULT_ID_TEAM).
                active(Boolean.TRUE).
                build();
    }

    @Factory
    public static ClubSupporter clubSupporter() {
        return ClubSupporter.builder().
                id(DEFAULT_ID).
                name(NAME).
                email(EMAIL).
                birthDate(BIRTH_DATE).
                team(Team.builder().id(DEFAULT_ID_TEAM).build()).
                active(Boolean.TRUE).
                build();
    }

    @Factory
    public static Team team(){
        return Team.builder().id(DEFAULT_ID_TEAM).name("Flamengo").build();
    }
}

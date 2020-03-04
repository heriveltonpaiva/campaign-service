package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.team.Team;
import org.hamcrest.Factory;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CampaignFactory {

    private static final Long DEFAULT_ID = 1L;

    public static final LocalDate START_DATE = LocalDate.now();

    public static final LocalDate END_DATE = LocalDate.now().plusDays(20L);

    @Factory
    public static List<Campaign> singleCampaignList() {
        final var campaign = Campaign.builder().
                id(DEFAULT_ID).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                team(Team.builder().build()).
                endEffectiveDate(END_DATE).
                build();
        return Collections.singletonList(campaign);
    }

    @Factory
    public static List<CampaignDTO> singleCampaignDtoList() {
        final var campaign = CampaignDTO.builder().
                id(DEFAULT_ID).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                build();
        return Collections.singletonList(campaign);
    }

    @Factory
    public static List<Campaign> manyCampaignList() {
         var firstCampaign = Campaign.builder().
                id(DEFAULT_ID).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(10L)).
                build();
        var secondCampaign = Campaign.builder().
                id(2L).
                name("Second Campaign").
                team(team()).
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(2L)).
                build();
        var thirdCampaign = Campaign.builder().
                id(3L).
                name("Third Campaign").
                team(team()).
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(1L)).
                build();
        return List.of(firstCampaign, secondCampaign, thirdCampaign);
    }

    @Factory
    public static List<Campaign> manyCampaignConflictDateList() {
        final var firstCampaign = Campaign.builder().
                id(DEFAULT_ID).
                name("First Campaign").
                team(team()).
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(21)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("Second Campaign").
                team(team()).
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(20L)).
                build();
        final var thirdCampaign = Campaign.builder().
                id(3L).
                name("Third Campaign").
                team(team()).
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(22L)).
                build();
        return List.of(firstCampaign, secondCampaign, thirdCampaign);
    }


    @Factory
    public static List<Campaign> manyCampaignActiveInactiveList() {
        final var firstCampaign = Campaign.builder().
                id(DEFAULT_ID).
                name("First Campaign Inactive").
                startEffectiveDate(START_DATE.minusDays(10L)).
                team(Team.builder().id(1L).build()).
                endEffectiveDate(END_DATE.minusDays(2L)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("First Campaign Active").
                startEffectiveDate(START_DATE).
                team(Team.builder().id(1L).build()).
                endEffectiveDate(END_DATE.plusDays(2L)).
                build();
        return List.of(firstCampaign, secondCampaign);
    }

    @Factory
    public static List<Campaign> manyCampaignInactiveList() {
        final var firstCampaign = Campaign.builder().
                id(DEFAULT_ID).
                name("First Campaign Inactive").
                startEffectiveDate(START_DATE.minusDays(10L)).
                endEffectiveDate(END_DATE.minusDays(2L)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("Second Campaign Inactive").
                startEffectiveDate(START_DATE.minusDays(5L)).
                endEffectiveDate(END_DATE.minusDays(3L)).
                build();
        return List.of(firstCampaign, secondCampaign);
    }

    @Factory
    public static CampaignRequest campaignRequest() {
        final var campaign = CampaignRequest.builder().
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                idHeartTeam(DEFAULT_ID).
                build();
        return campaign;
    }

    @Factory
    public static Campaign campaign() {
        final var campaign = Campaign.builder().
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                team(team()).
                build();
        return campaign;
    }
    @Factory
    public static Team team(){
        return Team.builder().id(DEFAULT_ID).name("Flamengo").build();
    }

}

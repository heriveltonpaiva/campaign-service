package br.com.hpaiva.campaignservice.campaign;

import org.hamcrest.Factory;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CampaignFactory {

    public static final LocalDateTime START_DATE = LocalDateTime.now();

    public static final LocalDateTime END_DATE = LocalDateTime.now().plusDays(20L);

    @Factory
    public static List<Campaign> singleCampaignList() {
        final var campaign = Campaign.builder().
                id(1L).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                build();
        return Collections.singletonList(campaign);
    }

    @Factory
    public static List<Campaign> manyCampaignList() {
        final var firstCampaign = Campaign.builder().
                id(1L).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(10L)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("Second Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(2L)).
                build();
        final var thirdCampaign = Campaign.builder().
                id(3L).
                name("Third Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE).
                build();
        return List.of(firstCampaign, secondCampaign, thirdCampaign);
    }

    @Factory
    public static List<Campaign> manyCampaignConflictDateList() {
        final var firstCampaign = Campaign.builder().
                id(1L).
                name("First Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(21)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("Second Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(20L)).
                build();
        final var thirdCampaign = Campaign.builder().
                id(3L).
                name("Third Campaign").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(22L)).
                build();
        return List.of(firstCampaign, secondCampaign, thirdCampaign);
    }


    @Factory
    public static List<Campaign> manyCampaignActiveInactiveList() {
        final var firstCampaign = Campaign.builder().
                id(1L).
                name("First Campaign Inactive").
                startEffectiveDate(START_DATE.minusDays(10L)).
                endEffectiveDate(END_DATE.minusDays(2L)).
                build();
        final var secondCampaign = Campaign.builder().
                id(2L).
                name("First Campaign Active").
                startEffectiveDate(START_DATE).
                endEffectiveDate(END_DATE.plusDays(2L)).
                build();
        return List.of(firstCampaign, secondCampaign);
    }

    @Factory
    public static List<Campaign> manyCampaignInactiveList() {
        final var firstCampaign = Campaign.builder().
                id(1L).
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
    public static Campaign campaign(){
        return singleCampaignList().stream().findFirst().get();
    }
}

package br.com.hpaiva.campaignservice.campaign;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.stream.Collectors;

import static br.com.hpaiva.campaignservice.campaign.CampaignFactory.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    @InjectMocks
    private CampaignServiceImpl service;

    @Mock
    private CampaignRepository repository;

    @Test
    public void shouldReturnEffectiveCampaign() {
        final var totalInactive = 2;

        when(repository.findAll()).thenReturn(manyCampaignActiveInactiveList());

        final var list = service.findCampaignsByIdHeartTeam(1L);

        list.stream().filter(campaign ->
                campaign.getEndEffectiveDate().isBefore(LocalDate.now())
        ).collect(Collectors.toList());

        Assertions.assertEquals(list.size(), totalInactive);
    }

    @Test
    public void shouldChangeEndDateForOneCampaignActive() {
        //given
        final var newCampaign = singleCampaignList().stream().findFirst().get();
        final var newDateUpdated = newCampaign.getEndEffectiveDate().plusDays(1);

        //when
        final var listUpdatedDates = service.changeCampaignDates(singleCampaignList());

        //then
        Assertions.assertEquals(listUpdatedDates.stream().findFirst().get().getEndEffectiveDate(), newDateUpdated);
    }

    @Test
    public void shouldChangeEndDateForSeveralCampaignActive() {
        //given
        final var newCampaign = singleCampaignList().stream().findFirst().get();

        //when
        final var list = service.changeCampaignDates(manyCampaignConflictDateList());

        //then
        final var listSameEndDate = list.stream().filter(campaign ->
                newCampaign.getEndEffectiveDate().equals(campaign.getEndEffectiveDate())).
                collect(Collectors.toList());
        Assertions.assertTrue(listSameEndDate.isEmpty());
    }

    @Test
    public void shouldSaveWhenInactiveCampaigns() {
        final var newCampaign = singleCampaignList().stream().findFirst().get();
        verify(service.save(null),timeout(1));
    }

    @Test
    public void shouldNotifyThatHasUpdateInCampaignExist() {
        service.update(1L, null);

    }

}

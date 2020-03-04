package br.com.hpaiva.campaignservice.campaign;

import br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterFactory;
import br.com.hpaiva.campaignservice.queue.AMQPProducer;
import br.com.hpaiva.campaignservice.team.TeamService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.hpaiva.campaignservice.campaign.CampaignFactory.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceTest {

    @InjectMocks
    private CampaignServiceImpl service;

    @Mock
    private CampaignRepository repository;

    @Mock
    private TeamService teamService;

    @Mock
    private AMQPProducer producer;

    @Test
    public void shouldReturnEffectiveCampaign() {
        final var totalInactive = 2;

        when(repository.findAllActivesByIdHeartTeam(1L)).thenReturn(manyCampaignActiveInactiveList());

        final var list = service.findCampaignsByIdHeartTeam(1L);

        list.stream().filter(campaign ->
                campaign.getEndEffectiveDate().isBefore(LocalDate.now())
        ).collect(Collectors.toList());

        Assertions.assertEquals(list.size(), totalInactive);
    }

    @Test
    public void shouldChangeEndDateForOneCampaignActive() {
        final var newCampaign = singleCampaignList().stream().findFirst().get();
        final var newDateUpdated = newCampaign.getEndEffectiveDate().plusDays(1);

        final var listUpdatedDates = service.validateConflictingPeriods(singleCampaignList(), newCampaign.getEndEffectiveDate());

        Assertions.assertEquals(listUpdatedDates.stream().findFirst().get().getEndEffectiveDate(), newDateUpdated);
    }

    @Test
    public void shouldChangeEndDateForSeveralCampaignActive() {
        final var newCampaign = singleCampaignList().stream().findFirst().get();

        final var list = service.validateConflictingPeriods(manyCampaignConflictDateList(), newCampaign.getEndEffectiveDate());

        final var listSameEndDate = list.stream().filter(campaign ->
                newCampaign.getEndEffectiveDate().equals(campaign.getEndEffectiveDate())).
                collect(Collectors.toList());
        Assertions.assertTrue(listSameEndDate.isEmpty());
    }

    @Test
    public void shouldCreateNewCampaign() throws NotFoundException {

        when(teamService.findById(ClubSupporterFactory.DEFAULT_ID_TEAM)).thenReturn(Optional.of(ClubSupporterFactory.team()));
        when(repository.findAllActivesByIdHeartTeam(ClubSupporterFactory.DEFAULT_ID_TEAM)).thenReturn(manyCampaignList());

        service.createCampaign(campaignRequest());

        verify(repository, times(1)).saveAll(manyCampaignList());
    }

    @Test
    public void shouldNotifyThatHasUpdateInCampaignExist() throws NotFoundException {

        when(repository.findById(ClubSupporterFactory.DEFAULT_ID)).thenReturn(Optional.of(campaign()));
        when(teamService.findById(ClubSupporterFactory.DEFAULT_ID_TEAM)).thenReturn(Optional.of(ClubSupporterFactory.team()));
        when(repository.findAllActivesByIdHeartTeam(ClubSupporterFactory.DEFAULT_ID_TEAM)).thenReturn(manyCampaignConflictDateList());

        service.updateCampaign(ClubSupporterFactory.DEFAULT_ID, campaignRequest());
        verify(repository, times(1)).saveAll(any());
        verify(producer, times(3)).sendMessage(any());

    }

}

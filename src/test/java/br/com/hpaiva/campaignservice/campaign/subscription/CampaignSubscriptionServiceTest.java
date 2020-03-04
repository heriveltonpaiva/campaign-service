package br.com.hpaiva.campaignservice.campaign.subscription;

import br.com.hpaiva.campaignservice.campaign.CampaignServiceImpl;
import javassist.NotFoundException;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.hpaiva.campaignservice.campaign.CampaignFactory.singleCampaignDtoList;
import static br.com.hpaiva.campaignservice.campaign.subscription.CampaignSubscriptionFactory.campaignSubscription;
import static br.com.hpaiva.campaignservice.clubsupporter.ClubSupporterFactory.clubSupporterDTO;
import static java.util.Collections.emptyList;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CampaignSubscriptionServiceTest {

    @InjectMocks
    private CampaignSubscriptionServiceImpl service;

    @Mock
    private CampaignSubscriptionRepository repository;

    @Mock
    private CampaignServiceImpl campaignService;

    private static final Long DEFAULT_ID_TEAM = 1L;

    @Test
    public void shouldReturnSubscriptionCampaignSaved() throws NotFoundException {
        when(campaignService.findCampaignsByIdHeartTeam(DEFAULT_ID_TEAM)).thenReturn(singleCampaignDtoList());

        service.subscription(clubSupporterDTO());

        verify(repository, times(1)).save(campaignSubscription());

    }

    @Test
    public void shouldThrowsExceptionSubscriptionCampaignFail() throws NotFoundException {

        when(campaignService.findCampaignsByIdHeartTeam(DEFAULT_ID_TEAM)).thenReturn(emptyList());

        Throwable thrown = assertThrows(NotFoundException.class, () -> {service.subscription(clubSupporterDTO());});

        assertThat(thrown.getMessage(), Matchers.is("Não há campanhas cadastradas para o time."));
    }

}

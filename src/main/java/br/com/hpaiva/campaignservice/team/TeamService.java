package br.com.hpaiva.campaignservice.team;

import java.util.Optional;

public interface TeamService {

    Optional<Team> findById(Long id);
}

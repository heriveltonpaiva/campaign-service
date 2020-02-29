package br.com.hpaiva.campaignservice.clubsupporter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubSupporterRepository extends CrudRepository<ClubSupporter, Long> {
}

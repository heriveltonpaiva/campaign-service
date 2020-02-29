package br.com.hpaiva.campaignservice.campaign;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

    //TODO verify how to filter localdate
    @Query("select c from Campaign c")
    List<Campaign> findAllActives();

}

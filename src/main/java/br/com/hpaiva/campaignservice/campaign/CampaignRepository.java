package br.com.hpaiva.campaignservice.campaign;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignRepository extends CrudRepository<Campaign, Long> {

    @Query("select c from Campaign c where c.team.id = :idHeartTeam and CURRENT_DATE between c.startEffectiveDate and c.endEffectiveDate")
    List<Campaign> findAllActivesByIdHeartTeam(@Param("idHeartTeam") Long idHeartTeam);

}

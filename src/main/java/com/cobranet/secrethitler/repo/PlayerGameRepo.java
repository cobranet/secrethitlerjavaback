package com.cobranet.secrethitler.repo;

import com.cobranet.secrethitler.model.PlayerGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PlayerGameRepo extends PagingAndSortingRepository<PlayerGame,Long> {
    @Query( "select p from PlayerGame p where userId = ?1 and ( gameState = 'PROPOSED'  or gameState='INPLAY')")
    PlayerGame getActiveGame( Long user_id);

    List<PlayerGame> findByGameId(Long gameId);
}

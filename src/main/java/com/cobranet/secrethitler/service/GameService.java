package com.cobranet.secrethitler.service;

import com.cobranet.secrethitler.model.Game;
import com.cobranet.secrethitler.model.PlayerGame;
import com.cobranet.secrethitler.repo.GameRepo;
import com.cobranet.secrethitler.repo.PlayerGameRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.DateTimeException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {

    @Autowired
    GameRepo gameRepo;

    @Autowired
    PlayerGameRepo playerGameRepo;


    public List<PlayerGame> joinGame(Long userId , Long gameId ){
        PlayerGame pg = playerGameRepo.getActiveGame(userId);
        if(pg != null){
            throw new RuntimeException("You have active game!");
        }
        List<PlayerGame> inGame = playerGameRepo.findByGameId(gameId);
        if ( inGame.size() == 0) {
            throw new RuntimeException("Invalid game!");
        }
        if( inGame.get(0).getGameState() != PlayerGame.GameState.PROPOSED ){
            throw new RuntimeException("You can't join that game!");
        }
        PlayerGame newpg = new PlayerGame();
        newpg.setGameState(PlayerGame.GameState.PROPOSED);
        newpg.setPlayerGameRole(PlayerGame.PlayerGameRole.CANDIDATE);
        newpg.setUserId(userId);
        newpg.setGameId(gameId);
        playerGameRepo.save(newpg);
        inGame = playerGameRepo.findByGameId(gameId);
        return inGame;
    }

    public void deleteGame(Long userId){
        PlayerGame pg = playerGameRepo.getActiveGame(userId);
        if(pg == null){
            throw new RuntimeException("You have no active game!");
        }
        List<PlayerGame> ingame = playerGameRepo.findByGameId(pg.getGameId());
        for (PlayerGame p: ingame) {
            playerGameRepo.deleteById(pg.getId());
        }
        gameRepo.deleteById(pg.getGameId());
    }

    public PlayerGame proposeGame(Long userId){
      PlayerGame pg =  playerGameRepo.getActiveGame(userId);
      if(pg != null )
          throw new RuntimeException("You have active game!");
      pg = new PlayerGame();
      Game g = new Game();
      gameRepo.save(g);
      pg.setGameState(PlayerGame.GameState.PROPOSED);
      pg.setPlayerGameRole(PlayerGame.PlayerGameRole.OWNER);
      pg.setGameId(g.getId());
      pg.setUserId(userId);
      playerGameRepo.save(pg);
      return pg;
    };

    public List<PlayerGame> myGame(Long userId){
        List<PlayerGame> result = new ArrayList<PlayerGame>();
        PlayerGame pg = playerGameRepo.getActiveGame(userId);
        if(pg == null ){
            return result;
        }
        List<PlayerGame> ingame = playerGameRepo.findByGameId(pg.getGameId());
        for (PlayerGame p: ingame) {
             result.add(p);
        }
        return result;
    }
}

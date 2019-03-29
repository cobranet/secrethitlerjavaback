package com.cobranet.secrethitler.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerGame {

    @Id
    @GeneratedValue
    private Long id;

    private Long gameId;
    private Long userId;

    public enum GameState {
        PROPOSED,
        INPLAY,
        FINISHED,
        CANCELED
    };

    public enum PlayerGameRole {
        OWNER,
        PLAYER,
        CANDIDATE
    }
    private PlayerGameRole playerGameRole;

    private GameState gameState;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(Long gameId) {
        this.gameId = gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public PlayerGameRole getPlayerGameRole() {
        return playerGameRole;
    }

    public void setPlayerGameRole(PlayerGameRole playerGameRole) {
        this.playerGameRole = playerGameRole;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public PlayerGame(){





    }
}

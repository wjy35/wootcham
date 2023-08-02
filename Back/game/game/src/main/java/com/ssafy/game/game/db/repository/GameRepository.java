package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Game;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
@ToString
@Getter
public class GameRepository {

    private Map<String, Game> games;

    public GameRepository() {
        this.games = new HashMap<>();
    }

    public void deleteGameByGameId(String gameId){
        this.games.remove(gameId);
    }

    public Game createNewGame(){
        Game game = new Game(UUID.randomUUID().toString());
        this.games.put(game.getGameId(),game);
        return game;
    }

    public Game findGameByGameId(String gameId){
        return this.games.get(gameId);
    }
}

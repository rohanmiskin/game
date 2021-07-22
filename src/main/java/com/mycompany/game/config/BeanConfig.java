package com.mycompany.game.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.mycompany.game.dao.GameDao;
import com.mycompany.game.dao.PlayerDao;
import com.mycompany.game.dao.ScoreDao;
import com.mycompany.game.model.dto.ScoreFileDTO;
import com.mycompany.game.model.entity.Game;
import com.mycompany.game.model.entity.Player;
import com.mycompany.game.model.entity.Score;
import com.mycompany.game.model.entity.ScoreKey;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Component
@EnableTransactionManagement
public class BeanConfig {
    private static final Type PLAYER_TYPE = new TypeToken<List<Player>>() {}.getType();
    private static final Type GAME_TYPE = new TypeToken<List<Game>>() {}.getType();

    @Autowired
    GameDao gameDao;

    @Autowired
    PlayerDao playerDao;

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    ObjectMapper objectMapper;

    @Bean("scoreQueueBean")
    public PriorityQueue<Score> getCache() {
//        PriorityBlockingQueue<Score> scoresQueue = new PriorityBlockingQueue(Comparator.comparingInt(a -> ));
//        PriorityBlockingQueue<Score> qu = new PriorityBlockingQueue<Score>(Comparator.comparingInt(a -> a))

        PriorityQueue<Score> scoresQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.getScoreValue()));
        return scoresQueue;

//        return scoresQueue;
    }

    @Bean
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void insertSampleData() throws FileNotFoundException {
        /*Game game = new Game();
        game.setGameId(2);
        game.setName("Cricket");

        gameDao.save(game);

        Player player = new Player();
        player.setPlayerId(30);
        player.setName("CricketPlayer");
        playerDao.save(player);

        Score score = new Score();
        score.setGame(game);
        score.setPlayer(player);
        score.setScoreValue(123);
        score.setScoreKey(new ScoreKey(game.getGameId(), player.getPlayerId()));

        scoreDao.save(score);*/

        String filePath = "src/main/resources/json/game-data.json";

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));

        List<Game> games = gson.fromJson(jsonReader,GAME_TYPE);
        gameDao.saveAll(games);

        filePath = "src/main/resources/json/players-data.json";


        jsonReader = new JsonReader(new FileReader(filePath));

        List<Player> players = gson.fromJson(jsonReader,PLAYER_TYPE);
        playerDao.saveAll(players);

    }

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper();
    }

}

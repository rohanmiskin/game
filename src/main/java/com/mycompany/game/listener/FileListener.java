package com.mycompany.game.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.mycompany.game.config.BeanConfig;
import com.mycompany.game.dao.GameDao;
import com.mycompany.game.dao.PlayerDao;
import com.mycompany.game.dao.ScoreDao;
import com.mycompany.game.model.dto.ScoreDTO;
import com.mycompany.game.model.dto.ScoreFileDTO;
import com.mycompany.game.model.entity.Score;
import com.mycompany.game.model.entity.ScoreKey;
import com.mycompany.game.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class FileListener {

    private static final Type SCORE_TYPE = new TypeToken<List<ScoreFileDTO>>() {}.getType();
    @Autowired
    ScoreDao scoreDao;

    @Autowired
    BeanConfig beanConfig;

    @Autowired
    PlayerDao playerDao;

    @Autowired
    GameDao gameDao;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    CacheService cacheService;

    @EventListener(classes = ApplicationStartedEvent.class)
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void readScoreData() throws IOException {
        String filePath = "src/main/resources/json/score-data.json";

        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(filePath));
        List<ScoreFileDTO> scoreDTOs = gson.fromJson(jsonReader,SCORE_TYPE);

        Score score;

        for (ScoreFileDTO scoreDTO : scoreDTOs){
            score = new Score();
            score.setGame(gameDao.getOne(scoreDTO.getGameId()));
            score.setPlayer(playerDao.getOne(scoreDTO.getPlayerId()));
            score.setScoreKey(new ScoreKey(scoreDTO.getGameId(),scoreDTO.getPlayerId()));
            score.setScoreValue(scoreDTO.getScoreValue());
            cacheService.updateCache(score);
            scoreDao.save(score);
        }

        System.out.println("dfdfd");




    }
}

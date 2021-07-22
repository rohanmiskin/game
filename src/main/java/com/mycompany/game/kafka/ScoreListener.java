package com.mycompany.game.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.game.dao.GameDao;
import com.mycompany.game.dao.PlayerDao;
import com.mycompany.game.dao.ScoreDao;
import com.mycompany.game.model.dto.ScoreFileDTO;
import com.mycompany.game.model.entity.Score;
import com.mycompany.game.model.entity.ScoreKey;
import com.mycompany.game.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Slf4j
@EnableBinding(Sink.class)
public class ScoreListener {

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    GameDao gameDao;

    @Autowired
    PlayerDao playerDao;

    @Autowired
    CacheService cacheService;

    @StreamListener(Sink.INPUT)
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void listen(GenericMessage<?> genericMessage){

        Object message = genericMessage.getPayload();
        log.info("Message received : "+message.toString());

        ScoreFileDTO scoreDTO = null;
        try{
            scoreDTO = objectMapper.readValue(message.toString(),ScoreFileDTO.class);
        }
        catch (IOException ex){
            log.error(ex.getClass().getSimpleName());
        }

        Score score = new Score();

        score.setGame(gameDao.getOne(scoreDTO.getGameId()));
        score.setPlayer(playerDao.getOne(scoreDTO.getPlayerId()));
        score.setScoreKey(new ScoreKey(scoreDTO.getGameId(),scoreDTO.getPlayerId()));
        score.setScoreValue(scoreDTO.getScoreValue());
        cacheService.updateCache(score);
        scoreDao.save(score);

        log.info("Message successfully persisted into cache and database");

    }
}

package com.mycompany.game.config;

import com.mycompany.game.dao.ScoreDao;
import com.mycompany.game.model.entity.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

@Component
public class CacheConfig {
    /*@Autowired
    StaticContextInitialization staticContextInitialization;*/

    @Autowired
    ScoreDao scoreDao;

    @Autowired
    @Qualifier("scoreQueueBean")
    private PriorityQueue<Score> scoresQueue;

    @EventListener(classes = ApplicationStartedEvent.class)
    public void initiateCache(){
        List<Score> topScores = scoreDao.getTopScires();
        scoresQueue.addAll(topScores);
    }
}

package com.mycompany.game.service;

import com.mycompany.game.constants.GameConstants;
import com.mycompany.game.model.entity.Score;
import org.apache.catalina.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.PriorityQueue;

@Service
public class CacheServiceImpl implements CacheService{

    @Autowired
    @Qualifier("scoreQueueBean")
    PriorityQueue<Score> cache;

    @Override
    public void updateCache(Score score) {
        cache.add(score);
        if(cache.size()> GameConstants.ResultLimit){
            while (cache.size()>GameConstants.ResultLimit) {
                cache.remove();
            }
        }
    }

    @Override
    public void clearCache() {
        cache.clear();
    }
}

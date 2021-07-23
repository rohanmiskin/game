package com.mycompany.game.service;

import com.mycompany.game.model.entity.Score;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.PriorityQueue;

@RunWith(MockitoJUnitRunner.class)
public class CacheServiceImplTest extends TestCase {

    @InjectMocks
    CacheServiceImpl cacheService;

    @Before
    public void setup(){
        PriorityQueue<Score> priorityQueue = new PriorityQueue<>();
        ReflectionTestUtils.setField(cacheService,"cache",priorityQueue);
    }

    @Test
    public void updateCacheTest(){
        Assert.assertTrue(cacheService.cache.size()==0);
        Score score = new Score();
        score.setScoreValue(1234);

        cacheService.updateCache(score);

        Assert.assertTrue(cacheService.cache.size()==1);
    }

    @Test
    public void clearCacheTest(){
        Score score = new Score();
        score.setScoreValue(1234);

        cacheService.updateCache(score);

        Assert.assertTrue(cacheService.cache.size()!=0);

        cacheService.clearCache();

        Assert.assertTrue(cacheService.cache.isEmpty());
    }

}
package com.mycompany.game.service;

import com.mycompany.game.model.dto.ScoreResponseDTO;
import com.mycompany.game.model.entity.Score;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.PriorityQueue;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest extends TestCase {

    @InjectMocks
    GameServiceImpl gameService;

    @Before
    public void setup(){
        PriorityQueue<Score> priorityQueue = new PriorityQueue<>();
        ReflectionTestUtils.setField(gameService,"cache",priorityQueue);
    }

    @Test
    public void testGetTopScores(){
        ScoreResponseDTO scoreResponseDTO = gameService.getTopScores();
        Assert.assertTrue(scoreResponseDTO.getScores().isEmpty());
    }
}
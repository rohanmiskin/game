package com.mycompany.game.controller;

import com.mycompany.game.model.dto.ScoreDTO;
import com.mycompany.game.model.dto.ScoreResponseDTO;
import com.mycompany.game.service.GameService;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(MockitoJUnitRunner.class)
public class GameControllerTest extends TestCase {

    @InjectMocks
    GameController gameController;

    @Mock
    GameService gameService;

    @Test
    public void testControllerPositive(){

        ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setPlayerName("Player1");
        scoreDTO.setGameName("BasketBall");
        scoreDTO.setScoreValue(1212);
        scoreResponseDTO.setScores(Arrays.asList(scoreDTO));
        Mockito.when(gameService.getTopScores()).thenReturn(scoreResponseDTO);

        ResponseEntity<ScoreResponseDTO> scoreResponseDTOResponseEntity = gameController.getTopScores();
        System.out.println(scoreResponseDTOResponseEntity.getStatusCode());
        Assert.assertEquals(HttpStatus.OK,scoreResponseDTOResponseEntity.getStatusCode());

        Assert.assertEquals(1,scoreResponseDTOResponseEntity.getBody().getScores().size());

    }

    @Test
    public void testControllerEmpty() {

        ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
        scoreResponseDTO.setScores(new ArrayList<>());

        Mockito.when(gameService.getTopScores()).thenReturn(scoreResponseDTO);
        ResponseEntity<ScoreResponseDTO> scoreResponseDTOResponseEntity = gameController.getTopScores();
        System.out.println(scoreResponseDTOResponseEntity.getStatusCode());

        Assert.assertEquals(HttpStatus.NO_CONTENT,scoreResponseDTOResponseEntity.getStatusCode());
    }


}
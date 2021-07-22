package com.mycompany.game.controller;

import com.mycompany.game.model.dto.ScoreResponseDTO;
import com.mycompany.game.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private
    GameService gameService;

    @GetMapping("/top")
    public ResponseEntity<ScoreResponseDTO> getTopScores(){
        return new ResponseEntity<ScoreResponseDTO>(gameService.getTopScores(),HttpStatus.OK);
    }
}

package com.mycompany.game.service;

import com.mycompany.game.model.dto.ScoreResponseDTO;


public interface GameService {
    ScoreResponseDTO getTopScores();
}

package com.mycompany.game.service;

import com.mycompany.game.model.dto.ScoreResponseDTO;
import com.mycompany.game.model.entity.Score;
import com.mycompany.game.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    @Qualifier("scoreQueueBean")
    private PriorityQueue<Score> cache;

    public ScoreResponseDTO getTopScores() {
        List<Score> scores = new ArrayList<>(cache);
        Collections.sort(scores);
        ScoreResponseDTO scoreResponseDTO = new ScoreResponseDTO();
        scoreResponseDTO.setScores(AppUtils.castScoreList(scores));
        return scoreResponseDTO;
    }
}

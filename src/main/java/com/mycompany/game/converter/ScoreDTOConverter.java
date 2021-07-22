package com.mycompany.game.converter;

import com.mycompany.game.model.dto.ScoreDTO;
import com.mycompany.game.model.entity.Score;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScoreDTOConverter implements Converter<Score, ScoreDTO> {

    @Override
    public ScoreDTO convert(Score score) {
        ScoreDTO scoreDTO = new ScoreDTO();
        scoreDTO.setGameName(score.getGame().getName());
        scoreDTO.setPlayerName(score.getPlayer().getName());
        scoreDTO.setScoreValue(score.getScoreValue());
        return scoreDTO;
    }
}

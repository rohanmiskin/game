package com.mycompany.game.util;

import com.mycompany.game.converter.ScoreDTOConverter;
import com.mycompany.game.model.dto.ScoreDTO;
import com.mycompany.game.model.entity.Score;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AppUtils {

    static ScoreDTOConverter scoreDTOConverter;

    public static void setScoreDTOConverter(ScoreDTOConverter converter) {
        scoreDTOConverter = converter;
    }

    public static List castScoreList(Collection<Score> collection) {
        List<ScoreDTO> result = new ArrayList<>();

        for (Score object : collection){
            result.add(scoreDTOConverter.convert(object));
        }
        return result;
    }
}

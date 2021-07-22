package com.mycompany.game.model.dto;

import com.mycompany.game.model.entity.Score;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreResponseDTO {
    List<ScoreDTO> scores = new ArrayList<>();
}

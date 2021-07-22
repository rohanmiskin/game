package com.mycompany.game.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScoreFileDTO {
    int gameId;
    int playerId;
    int scoreValue;
}

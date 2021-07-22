package com.mycompany.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ScoreKey implements Serializable {

    @Column(name = "GAME_ID",insertable = false,updatable = false)
    int gameId;

    @Column(name = "PLAYER_ID",insertable = false,updatable = false)
    int playerId;
}

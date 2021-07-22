package com.mycompany.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCORE")
public class Score {

    @EmbeddedId
    ScoreKey scoreKey;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "GAME_ID",referencedColumnName = "GAME_ID",insertable = false,updatable = false)
    Game game;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "PLAYER_ID",referencedColumnName = "PLAYER_ID",insertable = false,updatable = false)
    Player player;

    @Column(name = "SCORE_VALUE")
    Integer scoreValue;
}

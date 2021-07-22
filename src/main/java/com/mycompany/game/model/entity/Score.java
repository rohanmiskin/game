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
public class Score implements Comparable<Score>{

    @EmbeddedId
    ScoreKey scoreKey;

    @ManyToOne(fetch = FetchType.EAGER)
    Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    Player player;

    @Column(name = "SCORE_VALUE")
    Integer scoreValue;

    @Override
    public int compareTo(Score o) {
        return o.getScoreValue().compareTo(this.getScoreValue());
    }
}

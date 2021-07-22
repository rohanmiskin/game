package com.mycompany.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "GAME")
public class Game {

    @Id
    @Column(name="GAME_ID")
    int gameId;

    @Column(name = "NAME")
    String name;

    /*@OneToMany
    @JoinColumn(name = "GAME")
    List<Score> scoes;*/

}

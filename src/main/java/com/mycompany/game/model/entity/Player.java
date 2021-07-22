package com.mycompany.game.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PLAYER")
public class Player {

    @Id
    @Column(name = "PLAYER_ID")
    int playerId;

    @Column(name = "NAME")
    String name;


//    int age;
}

package com.mycompany.game.dao;

import com.mycompany.game.model.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameDao extends JpaRepository<Game,Integer> {
}

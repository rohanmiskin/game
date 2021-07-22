package com.mycompany.game.dao;

import com.mycompany.game.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerDao extends JpaRepository<Player,Integer> {
}

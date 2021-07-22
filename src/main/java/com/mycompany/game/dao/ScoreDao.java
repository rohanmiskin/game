package com.mycompany.game.dao;

import com.mycompany.game.model.entity.Score;
import com.mycompany.game.model.entity.ScoreKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDao extends JpaRepository<Score,ScoreKey> {

    @Query(value = "select * from SCORE order by SCORE_VALUE desc limit 5",nativeQuery = true)
    List<Score> getTopScires();
}

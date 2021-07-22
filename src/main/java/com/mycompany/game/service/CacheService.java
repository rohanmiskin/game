package com.mycompany.game.service;

import com.mycompany.game.model.entity.Score;

public interface CacheService {
    void updateCache(Score score);

    void clearCache();
}

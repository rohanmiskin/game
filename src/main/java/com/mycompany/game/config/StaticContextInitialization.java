package com.mycompany.game.config;

import com.mycompany.game.converter.ScoreDTOConverter;
import com.mycompany.game.util.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class StaticContextInitialization {

    @Autowired
    ScoreDTOConverter scoreDTOConverter;

    @PostConstruct
    public void init(){
        AppUtils.setScoreDTOConverter(scoreDTOConverter);
    }
}

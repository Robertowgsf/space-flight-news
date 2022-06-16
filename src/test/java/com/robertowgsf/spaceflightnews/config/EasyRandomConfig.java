package com.robertowgsf.spaceflightnews.config;

import org.jeasy.random.EasyRandom;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class EasyRandomConfig {
    @Bean
    public EasyRandom easyRandom() {
        return new EasyRandom();
    }
}

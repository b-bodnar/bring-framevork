package com.bobocode.hoverla.bring2;

import com.bobocode.hoverla.bring2.annotations.Bean;
import com.bobocode.hoverla.bring2.annotations.Value;

@Bean
public class RecommendatorImpl implements Recommendator {

    @Value("")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("RecommendatorImpl class , inject Value to field alcohol " + alcohol);
    }
}

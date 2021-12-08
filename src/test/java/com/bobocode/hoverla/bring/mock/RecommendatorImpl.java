package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Bean;
import com.bobocode.hoverla.bring.annotations.Value;

@Bean
public class RecommendatorImpl implements Recommendator {

    @Value("")
    private String alcohol;

    @Override
    public void recommend() {
        System.out.println("RecommendatorImpl class , inject Value to field alcohol " + alcohol);
    }
}

package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Bean;

@Bean
public class AngryPolicemanImpl implements Policeman {

    @Override
    public void saySomethings() {
        System.out.println("*********** AngryPolicemanImpl class ***********");
    }
}

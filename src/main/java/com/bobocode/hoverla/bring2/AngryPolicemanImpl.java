package com.bobocode.hoverla.bring2;

import com.bobocode.hoverla.bring2.annotations.Bean;

@Bean
public class AngryPolicemanImpl implements Policeman {

    @Override
    public void saySomethings() {
        System.out.println("*********** AngryPolicemanImpl class ***********");
    }
}

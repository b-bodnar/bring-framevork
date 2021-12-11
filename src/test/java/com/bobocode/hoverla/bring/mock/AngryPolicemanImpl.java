package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Component;

@Component
public class AngryPolicemanImpl implements Policeman {

    @Override
    public void saySomethings() {
        System.out.println("*********** AngryPolicemanImpl class ***********");
    }
}

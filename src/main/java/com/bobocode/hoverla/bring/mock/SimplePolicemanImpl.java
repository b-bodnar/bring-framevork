package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Component;

//@Component
public class SimplePolicemanImpl implements Policeman {

    @Override
    public void saySomethings() {
        System.out.println("*********** SimplePolicemanImpl class ***********");
    }
}

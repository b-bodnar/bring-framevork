package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Autowired;
import com.bobocode.hoverla.bring.annotations.Qualifier;

public class CoronaDesinfector {

    @Autowired
    private Announcer announcer ;

    @Autowired
    @Qualifier("AngryPolicemanImpl") //AngryPolicemanImpl  SimplePolicemanImpl
    private Policeman policeman ;

    public void start(Room room){
        announcer.announce("CoronaDesinfector class method start");
        policeman.saySomethings();
        desinfect(room);
        announcer.announce("CoronaDesinfector class method exit");
    }

    private void desinfect(Room room) {
        System.out.println("CoronaDesinfector method desinfect");
    }
}

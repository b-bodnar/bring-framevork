package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Autowired;
import com.bobocode.hoverla.bring.annotations.Qualifier;

public class CoronaDesinfector {


    @Autowired
    @Qualifier("AngryPolicemanImpl") //AngryPolicemanImpl  SimplePolicemanImpl
    private Policeman angryPoliceman;

    @Autowired
    @Qualifier("SimplePolicemanImpl") //AngryPolicemanImpl  SimplePolicemanImpl
    private Policeman policeman;

    @Autowired
    private Announcer announcer;

    @Autowired
    private Announcer announcer2;

    @Autowired
    private Recommendator recommendator;


    public void start(Room room) {
        announcer.announce("CoronaDesinfector class method start");
        announcer2.announce("O KURWA 2 ANNOUNCERA TUT YOOOOOOY!");
        policeman.saySomethings();
        angryPoliceman.saySomethings();
        desinfect(room);
        announcer.announce("CoronaDesinfector class method exit");
    }

    private void desinfect(Room room) {
        System.out.println("CoronaDesinfector method desinfect");
    }
}

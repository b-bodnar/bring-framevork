package com.bobocode.hoverla.bring.mock;

import com.bobocode.hoverla.bring.annotations.Autowired;
import com.bobocode.hoverla.bring.annotations.Component;

@Component
public class AnnouncerImpl implements Announcer {

    @Autowired
    private Recommendator recommendator ;

    @Override
    public void announce(String text) {
        recommendator.recommend();
        System.out.println(text);
    }
}

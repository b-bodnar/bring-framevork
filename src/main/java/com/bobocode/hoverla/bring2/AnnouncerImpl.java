package com.bobocode.hoverla.bring2;

import com.bobocode.hoverla.bring2.annotations.Autowired;
import com.bobocode.hoverla.bring2.annotations.Bean;

@Bean
public class AnnouncerImpl implements Announcer {

    @Autowired
    private Recommendator recommendator ;

    @Override
    public void announce(String text) {
        recommendator.recommend();
    }
}

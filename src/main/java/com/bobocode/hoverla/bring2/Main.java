package com.bobocode.hoverla.bring2;

import com.bobocode.hoverla.bring2.config.Application;
import com.bobocode.hoverla.bring2.config.ApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = Application.run("com.bobocode.hoverla.bring2");
        CoronaDesinfector desinfector = context.getObject(CoronaDesinfector.class);
        desinfector.start(new Room());
    }
}

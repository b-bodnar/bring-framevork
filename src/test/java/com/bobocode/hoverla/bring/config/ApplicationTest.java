package com.bobocode.hoverla.bring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest  {

    private ApplicationContext context;

    @Test
    public void testContextIsNotNull() {
        context = Application.run("com.bobocode.hoverla.bring");
        System.out.println(context);
        assertNotNull(context);
    }
}

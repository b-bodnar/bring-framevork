package com.bobocode.hoverla.bring.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest  {

    private ApplicationContext context;

    @BeforeEach
    public void setUp() throws Exception {
        context = Application.run("com.bobocode.hoverla.bring");
    }

    @Test
    public void testContextIsNotNull() {
        assertNotNull(context);
    }
}

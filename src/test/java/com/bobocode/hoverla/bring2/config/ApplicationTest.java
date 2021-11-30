package com.bobocode.hoverla.bring2.config;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicationTest  {

    private ApplicationContext context;

    @Before
    public void setUp() throws Exception {
        context = Application.run("com.bobocode.hoverla.bring2");
    }

    @Test
    public void testContextIsNotNull() {
        assertNotNull(context);
    }
}
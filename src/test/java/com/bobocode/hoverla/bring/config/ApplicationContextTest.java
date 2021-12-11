package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.mock.AngryPolicemanImpl;
import com.bobocode.hoverla.bring.mock.Policeman;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextTest {

    private ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = Application.run("com.bobocode.hoverla.bring");
    }

    @Test
    void testGetAngryPolicemanImplFromContext() {
        var actual = context.getObject(AngryPolicemanImpl.class);
        var expected = "com.bobocode.hoverla.bring.mock.AngryPolicemanImpl";

        assertEquals(actual.getClass().getName() , expected);
    }

    @Test
    void testGetAngryPolicemanImplByQualifier() {
        var actual = context.getObject(Policeman.class,"AngryPolicemanImpl");
        var expect = "com.bobocode.hoverla.bring.mock.AngryPolicemanImpl";

        assertEquals(actual.getClass().getName(),expect);
    }

    @Test
    void testGetSimplePolicemanImplByQualifier() {
        var actual = context.getObject(Policeman.class,"SimplePolicemanImpl");
        var expect = "com.bobocode.hoverla.bring.mock.SimplePolicemanImpl";

        assertEquals(actual.getClass().getName(),expect);
    }
}

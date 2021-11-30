package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.AngryPolicemanImpl;
import com.bobocode.hoverla.bring2.Policeman;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationContextTest {

    private ApplicationContext context;

    @Before
    void setUp() {
        context = Application.run("com.bobocode.hoverla.bring2");
    }

    @Test
    void testGetAngryPolicemanImplFromContext() {
        var actual = context.getObject(AngryPolicemanImpl.class);
        var expected = "com.bobocode.hoverla.bring2.AngryPolicemanImpl";

        assertEquals(actual.getClass().getName() , expected);
    }

    @Test
    void testGetAngryPolicemanImplByQualifier() {
        var actual = context.getObjectByQualifier(Policeman.class,"AngryPolicemanImpl");
        var expect = "com.bobocode.hoverla.bring2.AngryPolicemanImpl";

        assertEquals(actual.getClass().getName(),expect);
    }

    @Test
    void testGetSimplePolicemanImplByQualifier() {
        var actual = context.getObjectByQualifier(Policeman.class,"SimplePolicemanImpl");
        var expect = "com.bobocode.hoverla.bring2.SimplePolicemanImpl";

        assertEquals(actual.getClass().getName(),expect);
    }
}
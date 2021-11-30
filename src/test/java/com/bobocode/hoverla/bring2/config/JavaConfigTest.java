package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.AngryPolicemanImpl;
import com.bobocode.hoverla.bring2.Announcer;
import com.bobocode.hoverla.bring2.AnnouncerImpl;
import com.bobocode.hoverla.bring2.Policeman;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JavaConfigTest {

    private JavaConfig config = new JavaConfig("com.bobocode.hoverla.bring2");

    @Test
    void testConfigGetImplClassBySpecifyImplementationClassInSecondParameter() {
        var actual = config.getImplClassBy(Policeman.class,"AngryPoliceman");
        var expected = AngryPolicemanImpl.class;

        assertEquals(actual.getName(), expected.getName());
    }

    @Test
    void testConfigGetImplClassByWithInterfaceWhoHaveOneImplementation() {
        var actual = config.getImplClassBy(Announcer.class,"");
        var expected = AnnouncerImpl.class;

        assertEquals(actual.getName(), expected.getName());
    }


}
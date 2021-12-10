package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.mock.AngryPolicemanImpl;
import com.bobocode.hoverla.bring.mock.SimplePolicemanImpl;
import com.bobocode.hoverla.bring.mock.Announcer;
import com.bobocode.hoverla.bring.mock.AnnouncerImpl;
import com.bobocode.hoverla.bring.mock.Policeman;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JavaConfigTest {

    private BeanSearchConfigImpl config = new BeanSearchConfigImpl("com.bobocode.hoverla.bring");

    @Test
    void testConfigGetImplClassBySpecifyImplementationClassInSecondParameter() {
        var actual = config.getImplClassBy(Policeman.class);
        var expected = List.of(SimplePolicemanImpl.class, AngryPolicemanImpl.class);

        assertEquals(actual, expected);
    }

    @Test
    void testConfigGetImplClassByWithInterfaceWhoHaveOneImplementation() {
        var actual = config.getImplClassBy(Announcer.class);
        var expected = AnnouncerImpl.class;

        assertEquals(actual.get(0), expected);
    }


}

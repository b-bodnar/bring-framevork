//package com.bobocode.hoverla.bring.config;
//
//import com.bobocode.hoverla.bring.mock.AngryPolicemanImpl;
//import com.bobocode.hoverla.bring.mock.Announcer;
//import com.bobocode.hoverla.bring.mock.AnnouncerImpl;
//import com.bobocode.hoverla.bring.mock.Policeman;
//import org.junit.jupiter.api.Test;
//
//class JavaConfigTest {
//
//    private AutowiringConfigImpl config = new AutowiringConfigImpl("com.bobocode.hoverla.bring2");
//
//    @Test
//    void testConfigGetImplClassBySpecifyImplementationClassInSecondParameter() {
//        var actual = config.getImplClassBy(Policeman.class,"AngryPoliceman");
//        var expected = AngryPolicemanImpl.class;
//
//        assertEquals(actual.getName(), expected.getName());
//    }
//
//    @Test
//    void testConfigGetImplClassByWithInterfaceWhoHaveOneImplementation() {
//        var actual = config.getImplClassBy(Announcer.class,"");
//        var expected = AnnouncerImpl.class;
//
//        assertEquals(actual.getName(), expected.getName());
//    }
//
//
//}

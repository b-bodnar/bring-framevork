package com.bobocode.hoverla.bring;


import com.bobocode.hoverla.bring.annotation.Component;
import com.bobocode.hoverla.bring.mock.ParticipantService;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

public class BringFrameworkTest {

    @Before
    public void setUp() throws Exception {
        BringFramework.startApp(BringFrameworkTest.class);
    }

    @Test
    public void startApp() {
    }

    @Test
    public void getBeanTest() {
        List<String> participants = BringFramework.getBean(ParticipantService.class).getParticipants();

        System.out.println(BringFramework.getBean(ParticipantService.class).getParticipants());
    }

    @Test
    public void getImplementationTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("com.bobocode.hoverla.bring");
        Set<Class<?>> typesAnnotatedWith = reflections.getTypesAnnotatedWith(Component.class);
        

        System.out.println(typesAnnotatedWith);
    }
}

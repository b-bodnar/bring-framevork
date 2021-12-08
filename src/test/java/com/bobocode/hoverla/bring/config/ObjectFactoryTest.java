package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.mock.AngryPolicemanImpl;
import com.bobocode.hoverla.bring.beanPostProcessor.BeanPostProcessor;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ObjectFactoryTest {

    private ApplicationContext context = Application.run("com.bobocode.hoverla.bring2");;
    private List<BeanPostProcessor> configurators = new ArrayList<>();
    private ObjectFactory factory =  new ObjectFactory(context);

    @Test
    void testCreateObjectAngryPolicemanImpl() {
        var actual = factory.createObject(AngryPolicemanImpl.class);
        var expected = AngryPolicemanImpl.class;

        assertEquals(actual.getClass().getName(), expected.getName());
    }
}

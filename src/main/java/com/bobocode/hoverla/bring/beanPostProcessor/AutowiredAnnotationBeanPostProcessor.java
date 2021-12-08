package com.bobocode.hoverla.bring.beanPostProcessor;

import com.bobocode.hoverla.bring.config.ApplicationContext;
import com.bobocode.hoverla.bring.annotations.Autowired;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class AutowiredAnnotationBeanPostProcessor implements BeanPostProcessor {

    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext context) {
        Class<?> implClass = o.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)){
                field.setAccessible(true);
                Object object = context.getObject(field.getType());
                field.set(o,object);
            }
        }
    }
}

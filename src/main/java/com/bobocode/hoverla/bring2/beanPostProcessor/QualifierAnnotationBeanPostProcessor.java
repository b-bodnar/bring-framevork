package com.bobocode.hoverla.bring2.beanPostProcessor;

import com.bobocode.hoverla.bring2.config.ApplicationContext;
import com.bobocode.hoverla.bring2.annotations.Qualifier;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class QualifierAnnotationBeanPostProcessor implements BeanPostProcessor {

    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext context) {
        Class<?> implClass = o.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Qualifier annotation = field.getAnnotation(Qualifier.class);
            if (annotation!= null){
                String qualifier = annotation.value();
                field.setAccessible(true);
                Object object = context.getObjectByQualifier(field.getType(), qualifier);
                field.set(o,object);
            }
        }
    }
}

package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.annotations.Bean;
import org.reflections.Reflections;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BeanDefinitionsBuilder {

    Map<String, BeanDefinition> beanDefinitions;

    private BeanDefinitionsBuilder(String packageToScan) {
        Reflections scanner = new Reflections(packageToScan);
        this.beanDefinitions = scanner.getTypesAnnotatedWith(Bean.class)
                .stream()
                .map(this::createDefinition)
                .collect(Collectors.toMap(BeanDefinition::getBeanName, beanDefinition -> beanDefinition));
    }

    public static Map<String, BeanDefinition> getBeanDefinitions(String packageToScan) {
        return new BeanDefinitionsBuilder(packageToScan).beanDefinitions;
    }

    // TODO: add support for other fields
    private BeanDefinition createDefinition(Class<?> clazz) {
        return BeanDefinition.builder()
                .beanClass(clazz)
                .beanName(createBeanName(clazz))
                .build();
    }

    private String createBeanName(Class<?> clazz) {
        Bean annotation = clazz.getAnnotation(Bean.class);
        if (annotation.value().isEmpty()) {
            return clazz.getSimpleName().replace(clazz.getSimpleName().charAt(0), Character.toLowerCase(clazz.getSimpleName().charAt(0)));
        }
        return annotation.value();
    }

}

package com.bobocode.hoverla.bring2.beanPostProcessor;

import com.bobocode.hoverla.bring2.config.ApplicationContext;
import com.bobocode.hoverla.bring2.annotations.Value;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public class ValueAnnotationBeanPostProcessor implements BeanPostProcessor {

    private Map<String, String> propertiesMap;

    @SneakyThrows
    public ValueAnnotationBeanPostProcessor() {
        String path = ClassLoader.getSystemClassLoader().getResource("application.properties").getPath();
        Stream<String> lines = new BufferedReader(new FileReader(path)).lines();
        propertiesMap = lines.map(line -> line.split("=")).collect(toMap(arr -> arr[0], arr -> arr[1]));
    }

    @SneakyThrows
    @Override
    public void configure(Object o, ApplicationContext context) {
        Class<?> implClass = o.getClass();
        for (Field field : implClass.getDeclaredFields()) {
            Value annotation = field.getAnnotation(Value.class);
            if (annotation!= null){
                String value;
                if(annotation.value().isEmpty()){
                    value = propertiesMap.get(field.getName());
                }else {
                    value  = propertiesMap.get(annotation.value());
                }
                field.setAccessible(true);
                field.set(o,value);
            }
        }
    }
}

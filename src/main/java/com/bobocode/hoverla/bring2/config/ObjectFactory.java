package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.beanPostProcessor.BeanPostProcessor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;

public class ObjectFactory   {

    private final ApplicationContext context;
    private List<BeanPostProcessor> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanPostProcessor> aClass : context.getConfig().getScanner().getSubTypesOf(BeanPostProcessor.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass){
        T t = implClass.getDeclaredConstructor().newInstance();

        //chain of responsibility
        configurators.forEach(beanPostProcessor -> beanPostProcessor.configure(t,context));

        return t;
    }

}

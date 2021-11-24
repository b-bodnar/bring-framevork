package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.beanPostProcessor.BeanPostProcessor;
import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanFactory {

    private final ApplicationContext context;
    private List<BeanPostProcessor> configurators = new ArrayList<>();

    /**
     * Map of bean definition objects, keyed by bean name
     */
    private final Map<String, BeanDefinition> beanDefinitionMap;

    @SneakyThrows
    public BeanFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanPostProcessor> aClass : context.getConfig().getScanner().getSubTypesOf(BeanPostProcessor.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
        beanDefinitionMap = BeanDefinitionsBuilder.getBeanDefinitions(context.getConfig().getBasePackageToScan());
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass){
        T t = implClass.getDeclaredConstructor().newInstance();

        //chain of responsibility
        configurators.forEach(beanPostProcessor -> beanPostProcessor.configure(t,context));

        return t;
    }


    public String[] getBeanDefinitionNames() {
        return this.beanDefinitionMap.keySet().toArray(new String[0]);
    }

}

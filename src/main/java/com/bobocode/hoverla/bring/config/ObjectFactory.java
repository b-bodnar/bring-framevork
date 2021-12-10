package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.beanPostProcessor.BeanPostProcessor;
import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectFactory   {

    private final ApplicationContext context;
    private List<BeanPostProcessor> configurators = new ArrayList<>();

    @SneakyThrows
    public ObjectFactory(ApplicationContext context) {
        this.context = context;
        for (Class<? extends BeanPostProcessor> aClass : context.getBeanSearchConfig().getScanner().getSubTypesOf(BeanPostProcessor.class)) {
            configurators.add(aClass.getDeclaredConstructor().newInstance());
        }
    }

    @SneakyThrows
    public <T> T createObject(Class<T> implClass){
//        Constructor<?>[] declaredConstructors = implClass.getDeclaredConstructors();
//        for (Constructor<?> declaredConstructor : declaredConstructors) {
//            System.out.println(Arrays.toString(declaredConstructor.getParameters()));
//        }
//        Parameter[] parameters = implClass.getDeclaredConstructor().getParameters();
//        int length = implClass.getDeclaredConstructors().length;
        if (implClass.getDeclaredConstructors().length > 1 || implClass.getDeclaredConstructors()[0].getParameters().length > 0) {
            throw new IllegalArgumentException("Please use field injection with @Autowired annotation. Constructor injection is not supported for now. Will be made in the future ;)");
        }
        T t = implClass.getDeclaredConstructor().newInstance();

        //chain of responsibility
        configurators.forEach(beanPostProcessor -> beanPostProcessor.configure(t,context));

        return t;
    }

}

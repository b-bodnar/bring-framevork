package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.annotations.Bean;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class,Object> cache = new ConcurrentHashMap<>();
    @Getter
    private AutowiringConfig autowiringConfig;

    public ApplicationContext(AutowiringConfig config) {
        this.autowiringConfig = config;
    }

    public <T> T getObject(Class<T> type){
        if (cache.containsKey(type)){
            return (T) cache.get(type);
        }

        Class<? extends T>  implClass = type;
        if (type.isInterface()){
            implClass = autowiringConfig.getImplClassBy(type, "");
        }

        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Bean.class)){
            cache.put(type, implClass);
        }

        return t;
    }

    public <T> T getObjectByQualifier(Class<T> type, String value) {
        Class<? extends T>  implClass = type;
        if (type.isInterface()){
            implClass = autowiringConfig.getImplClassBy(type,value);
        }

        T t = factory.createObject(implClass);
        return t;
    }
}

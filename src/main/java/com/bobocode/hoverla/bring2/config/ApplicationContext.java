package com.bobocode.hoverla.bring2.config;

import com.bobocode.hoverla.bring2.annotations.Bean;
import com.bobocode.hoverla.bring2.exceptions.MultipleBeanImplementationsException;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private Map<Class,Object> cache = new ConcurrentHashMap<>();
    @Getter
    private Config config;

    public ApplicationContext(Config config) {
        this.config = config;
    }

    public <T> T getObject(Class<T> type){
        if (cache.containsKey(type)){
            return (T) cache.get(type);
        }

        Class<? extends T>  implClass = type;
        if (type.isInterface()){
            implClass = config.getImplClassBy(type, "");
        }

        T t = factory.createObject(implClass);

        if (implClass.isAnnotationPresent(Bean.class)){
            cache.put(type, implClass);
        }

        return t;
    }

    public <T> T getObjectByQualifier(Class<T> type, String qualifier) {
        Class<? extends T>  implClass = type;

        if (qualifier.isEmpty()){
            throw new MultipleBeanImplementationsException( type + " has more than one implementation. Please use @Qualifier annotation to specify which implementation you want to use.");
        }

        if (type.isInterface()){
            implClass = config.getImplClassBy(type,qualifier);
        }

        T t = factory.createObject(implClass);
        return t;
    }
}

package com.bobocode.hoverla.bring.config;


import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class AutowiringConfigImpl implements AutowiringConfig {

    @Getter
    private final Reflections scanner;
    private final Map<Class<?>,Class<?>> qualifierCache = new ConcurrentHashMap<>();

    public AutowiringConfigImpl(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClassBy(Class<T> ifc, String qualifier) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        Class<? extends T> result = classes.iterator().next();

        if (!qualifier.isEmpty()){
            putQualifierImplToCache(ifc, qualifier, classes);
        }

        if (qualifierCache.containsKey(ifc)){
            for (Map.Entry<Class<?>, Class<?>> classClassEntry : qualifierCache.entrySet()) {
                result = (Class<? extends T>) classClassEntry.getValue();
            }
        }

        return result;
    }

    private <T> void putQualifierImplToCache(Class<T> ifc, String qualifier, Set<Class<? extends T>> classes) {
        for (Class<? extends T> aClass : classes) {
            if (aClass.toString().matches(".*" + qualifier + ".*")) {
                qualifierCache.put(ifc,aClass);
            }
        }
    }
}

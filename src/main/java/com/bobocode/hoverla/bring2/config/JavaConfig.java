package com.bobocode.hoverla.bring2.config;


import lombok.Getter;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class JavaConfig implements Config {

    @Getter
    private Reflections scanner;
    private Map<Class,Class> qualifierCache = new ConcurrentHashMap<>();

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClassBy(Class<T> ifc, String qualifier) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        Class<? extends T> result = classes.iterator().next();

        if (qualifierCache.containsKey(ifc)){
            for (Map.Entry<Class, Class> classClassEntry : qualifierCache.entrySet()) {
                result = classClassEntry.getValue();
            }
        }

        if (!qualifier.isEmpty()){
            putQualifierImplToCache(ifc, qualifier, classes);
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

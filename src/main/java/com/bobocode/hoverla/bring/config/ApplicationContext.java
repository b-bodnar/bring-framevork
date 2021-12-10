package com.bobocode.hoverla.bring.config;

import com.bobocode.hoverla.bring.annotations.Component;
import com.bobocode.hoverla.bring.exception.NoSuchImplementationException;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ApplicationContext {
    @Setter
    private ObjectFactory factory;
    private final Multimap<Class, Object> cache = ArrayListMultimap.create();
    @Getter
    private final BeanSearchConfig beanSearchConfig;

    public ApplicationContext(BeanSearchConfig config) {
        this.beanSearchConfig = config;
    }

    public <T> T getObject(Class<T> type) {
        return getObject(type, "");
    }

    public <T> T getObject(Class<T> type, String qualifier) {
        System.out.println(cache);
        if (cache.containsKey(type)) {
            return getObjectFromCache(type, qualifier);
        }

        return type.isInterface() ?
            getObjectFromInterface(type, qualifier) : getObjectFromClass(type);
    }

    private <T> T getObjectFromCache(Class<T> type, String qualifier) {
        Collection<Object> objects = cache.get(type);
        if (objects.size() >= 1 || !qualifier.isBlank()) {
            for (Object object : objects) {
                if (qualifier.equals(object.getClass().getSimpleName())) {
                    return (T) object;
                }
            }
        }
        return (T) new ArrayList<>(objects).get(0);

    }

    private <T> T getObjectFromInterface(Class<T> type, String qualifier) {
        System.out.println(type);
        List<Class<? extends T>> implClasses = beanSearchConfig.getImplClassBy(type);
        return implClasses.size() > 1 ?
            getObjectFromInterfaceWithMultipleImpls(type, qualifier, implClasses)
            : getObjectFromInterfaceWithSingleImpl(type, implClasses);
    }

    private <T> T getObjectFromInterfaceWithSingleImpl(Class<T> type, List<Class<? extends T>> implClasses) {
        T t = factory.createObject((Class<? extends T>) implClasses.get(0));

        if (implClasses.get(0).isAnnotationPresent(Component.class)) {
            cache.put(type, t);
        }
        return t;
    }

    private <T> T getObjectFromInterfaceWithMultipleImpls(Class<T> type, String qualifier, List<Class<? extends T>> implClasses) {
        Set<? extends T> objects = implClasses.stream()
            .map(impl -> factory.createObject(impl))
            .collect(Collectors.toSet());
        for (T object : objects) {
            if (object.getClass().isAnnotationPresent(Component.class)) {
                cache.put(type, object);
            }
        }
        List<String> implNames = implClasses.stream().map(Class::getSimpleName).collect(Collectors.toList());
        return objects.stream()
            .filter(obj -> qualifier.equals(obj.getClass().getSimpleName()))
            .findAny()
            .orElseThrow(() ->
                new NoSuchImplementationException("This interface has multiple implementations, please specify the qualifier. Available implementations: " + implNames));
    }

    private <T> T getObjectFromClass(Class<T> type) {
        System.out.println(type);
        T t = factory.createObject((Class<? extends T>) type);

        if (type.isAnnotationPresent(Component.class)) {
            cache.put(type, t);
        }
        return t;
    }
}

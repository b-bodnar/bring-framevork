package com.bobocode.hoverla.bring.config;

import org.reflections.Reflections;

import java.util.List;

public interface BeanSearchConfig {
    <T> List<Class<? extends T>> getImplClassBy(Class<T> ifc);
    Reflections getScanner();
    Reflections getConfigScanner();
}

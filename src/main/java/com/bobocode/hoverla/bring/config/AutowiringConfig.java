package com.bobocode.hoverla.bring.config;

import org.reflections.Reflections;

public interface AutowiringConfig {
    <T> Class<? extends T> getImplClassBy(Class<T> ifc, String value);
    Reflections getScanner();
}

package com.bobocode.hoverla.bring2.config;

import org.reflections.Reflections;

public interface Config {
    <T> Class<? extends T> getImplClassBy(Class<T> ifc, String value);

    Reflections getScanner();

    String getBasePackageToScan();
}

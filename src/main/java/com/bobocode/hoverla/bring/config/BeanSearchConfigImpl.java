package com.bobocode.hoverla.bring.config;


import com.bobocode.hoverla.bring.exception.NoSuchImplementationException;
import lombok.Getter;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BeanSearchConfigImpl implements BeanSearchConfig {

    @Getter
    private final Reflections scanner;
    @Getter
    private final Reflections configScanner;

    public BeanSearchConfigImpl(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
        this.configScanner = new Reflections("com.bobocode.hoverla.bring");
    }

    @Override
    public <T> List<Class<? extends T>> getImplClassBy(Class<T> ifc) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        if (classes.size() == 0) {
            throw new NoSuchImplementationException(ifc + " has no implementation");
        }
        return new ArrayList<>(classes);
    }

}

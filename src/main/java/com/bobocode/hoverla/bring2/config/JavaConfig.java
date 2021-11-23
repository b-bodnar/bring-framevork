package com.bobocode.hoverla.bring2.config;


import lombok.Getter;
import org.reflections.Reflections;
import java.util.Set;

public class JavaConfig implements Config {

    @Getter
    private Reflections scanner;

    public JavaConfig(String packageToScan) {
        this.scanner = new Reflections(packageToScan);
    }

    @Override
    public <T> Class<? extends T> getImplClassBy(Class<T> ifc, String qualifier) {
        Set<Class<? extends T>> classes = scanner.getSubTypesOf(ifc);
        Class<? extends T> aClass = getImplClassByQualifier(qualifier, classes);
        if (aClass != null) return aClass;
        return classes.iterator().next();
    }

    private <T> Class<? extends T> getImplClassByQualifier(String value, Set<Class<? extends T>> classes) {
        if (!value.isEmpty()){
            for (Class<? extends T> aClass : classes) {
                if (aClass.toString().matches(".*"+ value +".*")){
                    return aClass;
                }
            }
        }
        return null;
    }
}

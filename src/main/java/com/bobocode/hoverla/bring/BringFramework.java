package com.bobocode.hoverla.bring;

import com.bobocode.hoverla.bring.mock.ParticipantService;
import com.bobocode.hoverla.bring.mock.ParticipantServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class BringFramework {

    private Map<Class<?>, Class<?>> diMap;
    private Map<Class<?>, Object> appContext;

    private static BringFramework diContainer;

    private BringFramework() {
        diMap = new HashMap<>();
        appContext = new HashMap<>();
    }

    public static void startApp(Class<?> mainClass) {
        if (diContainer == null) {
            diContainer = new BringFramework();
            diContainer.initializeFramework(mainClass);
        }
    }

    private void initializeFramework(Class<?> mainClass) {
        /*
        * todo:
        *  - get all classes in the package annotated by Component.class and add to diMap by their Classws and Interfaces
        *  - make instances of all classes with Component annotation, put them to appContext and not forget to take care of injection
        * dependencies declared inside :)
        *
        * Remove redundant code below
        * */

        diMap.put(ParticipantServiceImpl.class, ParticipantService.class);
    }

    public static <T> T getBean(Class<T> clazz) {
        try {
            return diContainer.getBeanInstance(clazz);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    private <T> T getBeanInstance(Class<T> interfaceClass) throws InstantiationException, IllegalAccessException {
        return (T) getBeanInstance(interfaceClass, null, null);
    }

    private <T> Object getBeanInstance(Class<T> interfaceClass, String fieldName, String qualifier) throws InstantiationException, IllegalAccessException {
        Class<?> implementationClass = getImplementation(interfaceClass, fieldName, qualifier);

        if (appContext.containsKey(implementationClass)) {
            return appContext.get(implementationClass);
        }

        Object bean = implementationClass.newInstance();
        appContext.put(implementationClass, bean);
        return bean;
    }

    private <T> Class<?> getImplementation(Class<T> interfaceClass, String fieldName, String qualifier) {
        Set<Map.Entry<Class<?>, Class<?>>> implementationClasses = diMap.entrySet().stream().filter(entry -> entry.getValue() == interfaceClass).collect(Collectors.toSet());
        String errorMessage;
        if (implementationClasses.size() == 0) {
            errorMessage = "No impl found for interface " + interfaceClass.getName();
        } else if (implementationClasses.size() == 1) {
            Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClasses.stream().findFirst();
            return optional.get().getKey();
        } else {
            String findBy = (qualifier.isEmpty()) ? fieldName : qualifier;
            Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClasses.stream().filter(entry -> entry.getKey().getSimpleName().equalsIgnoreCase(findBy)).findAny();
            if (optional.isPresent()) {
                return optional.get().getKey();
            } else  {
                errorMessage = "There are " + implementationClasses.size() + " of interface " + interfaceClass.getName()
                        + ". Provide single implementation or make use of Qualifier.class to resolve conflict";
            }
        }
        throw  new RuntimeException(new Error(errorMessage));
    }
}

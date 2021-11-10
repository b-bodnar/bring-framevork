package com.bobocode.hoverla.bring;

import com.bobocode.hoverla.bring.annotation.Component;
import com.bobocode.hoverla.bring.exception.BeanImplementationNotFoundException;
import com.bobocode.hoverla.bring.exception.MultipleBeanImplementationsException;
import org.reflections.Reflections;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class BringFramework {

  private Map<Class<?>, Class<?>> diMap;
  private Map<Class<?>, Object> appContext;

  private static BringFramework diContainer;

  private BringFramework() {
    super();
    diMap = new HashMap<>();
    appContext = new HashMap<>();
  }

  /**
   * Start application
   *
   * @param mainClass
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public static void startApp(Class<?> mainClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    if (diContainer == null) {
      diContainer = new BringFramework();
      diContainer.initializeFramework(mainClass);
    }
  }

  /**
   * Initialize bring framework, scanning package of main class using reflections API for classes marked with
   * Component.class annotation. Adding these classes to application context Map and to dependency injection Map
   *
   * @param mainClass
   * @throws NoSuchMethodException
   * @throws InvocationTargetException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  private void initializeFramework(Class<?> mainClass) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    var reflections = new Reflections(mainClass.getPackageName());

    Set<Class<?>> componentClasses = reflections.getTypesAnnotatedWith(Component.class);
    for (Class<?> implClass : componentClasses) {
      addClassToAppContext(implClass);
      addClassesAndImplToDiMap(implClass);
    }

  }

  private void addClassesAndImplToDiMap(Class<?> implClass) {
    var interfaces = implClass.getInterfaces();
    if (interfaces.length == 0)
      diMap.put(implClass, implClass);
    else
      for (Class<?> iface : interfaces) {
        diMap.put(implClass, iface);
      }
  }

  private Object addClassToAppContext(Class<?> cls) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    var clazz = cls.getDeclaredConstructor().newInstance();
    appContext.put(cls, clazz);
    return clazz;
  }

  public static <T> T getBean(Class<T> clazz) {
    try {
      return diContainer.getBeanInstance(clazz);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  public <T> T getBeanInstance(Class<T> interfaceClass) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
    return (T) getBeanInstance(interfaceClass, null, null);
  }

  public <T> Object getBeanInstance(Class<T> interfaceClass, String fieldName, String qualifier) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
    Class<?> implementationClass = getImplementation(interfaceClass, fieldName, qualifier);

    if (appContext.containsKey(implementationClass)) {
      return appContext.get(implementationClass);
    }

    Object bean = implementationClass.getDeclaredConstructor().newInstance();
    appContext.put(implementationClass, bean);
    return bean;
  }

  private <T> Class<?> getImplementation(Class<T> interfaceClass, String fieldName, String qualifier) {
    Set<Map.Entry<Class<?>, Class<?>>> implementationClasses = diMap.entrySet().stream().filter(entry -> entry.getValue().equals(interfaceClass)).collect(Collectors.toSet());
    if (implementationClasses.isEmpty()) {
      throw new BeanImplementationNotFoundException("No impl found for interface " + interfaceClass.getName());
    } else if (implementationClasses.size() == 1) {
      Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClasses.stream().findFirst();
      return optional.get().getKey();
    } else {
      String findBy = (qualifier.isEmpty()) ? fieldName : qualifier;
      Optional<Map.Entry<Class<?>, Class<?>>> optional = implementationClasses.stream()
          .filter(entry -> entry.getKey().getSimpleName().equalsIgnoreCase(findBy)).findAny();
      if (optional.isPresent()) {
        return optional.get().getKey();
      } else {
        throw new MultipleBeanImplementationsException("There are " + implementationClasses.size() + " of interface " + interfaceClass.getName()
            + ". Provide single implementation or make use of Qualifier.class to resolve conflict");
      }
    }
  }

}

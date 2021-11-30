package com.bobocode.hoverla.bring;


import com.bobocode.hoverla.bring.annotation.Component;
import com.bobocode.hoverla.bring.exception.BeanImplementationNotFoundException;
import com.bobocode.hoverla.bring.exception.MultipleBeanImplementationsException;
import com.bobocode.hoverla.bring.mock.BobocodeComponent;
import com.bobocode.hoverla.bring.mock.MultipleImplExceptionService;
import com.bobocode.hoverla.bring.mock.ParticipantService;
import org.junit.Before;
import org.junit.Test;
import org.reflections.Reflections;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class BringFrameworkTest {
  private Map<Class<?>, Object> appContext;
  private Set<Class<?>> componentClasses;

  @Before
  public void setUp() throws Exception {
    Reflections reflections = new Reflections("com.bobocode.hoverla.bring");
    appContext = BringFramework.startApp(BringFrameworkTest.class);
    componentClasses = reflections.getTypesAnnotatedWith(Component.class);
  }


  @Test
  public void startApp() throws Exception {
    assertNotNull(BringFramework.startApp(BringFrameworkTest.class));
  }

  @Test
  public void getBeanTest() {
    ParticipantService participantBean = BringFramework.getBean(ParticipantService.class);
    BobocodeComponent bobocodeBean = BringFramework.getBean(BobocodeComponent.class);
    assertNotNull(participantBean);
    assertNotNull(bobocodeBean);
    assertNotNull(participantBean.getParticipants());
  }

  @Test
  public void getApplicationContextTest() {

    assertFalse(appContext.isEmpty());
    assertEquals(componentClasses, appContext.keySet());
  }

  @Test(expected = BeanImplementationNotFoundException.class)
  public void BeanImplementationNotFoundExceptionTest() {
    BringFramework.getBean(Object.class);
  }

  @Test(expected = MultipleBeanImplementationsException.class)
  public void MultipleBeanImplementationsException(){
    BringFramework.getBean(MultipleImplExceptionService.class);
  }
}

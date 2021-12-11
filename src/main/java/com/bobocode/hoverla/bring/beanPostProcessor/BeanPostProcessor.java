package com.bobocode.hoverla.bring.beanPostProcessor;

import com.bobocode.hoverla.bring.config.ApplicationContext;

public interface BeanPostProcessor {
    void configure(Object o, ApplicationContext context);
}

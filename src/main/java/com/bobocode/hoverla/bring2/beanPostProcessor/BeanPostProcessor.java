package com.bobocode.hoverla.bring2.beanPostProcessor;

import com.bobocode.hoverla.bring2.config.ApplicationContext;

public interface BeanPostProcessor {
    void configure(Object o, ApplicationContext context);
}

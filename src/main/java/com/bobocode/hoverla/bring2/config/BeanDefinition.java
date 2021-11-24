package com.bobocode.hoverla.bring2.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BeanDefinition {

    private String beanName;

    private Class<?> beanClass;

    private boolean lazyInit;

    private String initMethodName;

    private String destroyMethodName;

    // TODO: add more fields if needed

}

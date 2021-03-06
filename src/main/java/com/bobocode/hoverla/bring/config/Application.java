package com.bobocode.hoverla.bring.config;

public class Application {
    public static ApplicationContext run(String packageToScan){
        BeanSearchConfigImpl config = new BeanSearchConfigImpl(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        return context;
    }
}

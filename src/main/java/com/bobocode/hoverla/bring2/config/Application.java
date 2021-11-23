package com.bobocode.hoverla.bring2.config;

public class Application {
    public static ApplicationContext run(String packageToScan){
        JavaConfig config = new JavaConfig(packageToScan);
        ApplicationContext context = new ApplicationContext(config);
        ObjectFactory factory = new ObjectFactory(context);
        context.setFactory(factory);
        return context;
    }
}

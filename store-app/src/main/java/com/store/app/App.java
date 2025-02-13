package com.store.app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.store.app.configuration.AppConfiguration;
import com.store.app.core.Core;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        Core core = context.getBean(Core.class);
        core.launch();
        ((AbstractApplicationContext) context).close();

    }
}

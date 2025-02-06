package com.store;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.store.configuration.AppConfiguration;
import com.store.core.ICore;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        ICore core = context.getBean(ICore.class);
        core.launch();
    }
}
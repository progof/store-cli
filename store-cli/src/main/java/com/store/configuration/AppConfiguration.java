package com.store.configuration;

import org.springframework.context.annotation.*;

import java.util.Scanner;

@Configuration
@ComponentScan("com.store")
public class AppConfiguration {

    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}

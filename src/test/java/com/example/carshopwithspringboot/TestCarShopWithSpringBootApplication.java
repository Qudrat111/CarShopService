package com.example.carshopwithspringboot;

import org.springframework.boot.SpringApplication;

public class TestCarShopWithSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.from(CarShopWithSpringBootApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

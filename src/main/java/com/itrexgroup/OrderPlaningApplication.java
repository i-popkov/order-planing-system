package com.itrexgroup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class OrderPlaningApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderPlaningApplication.class, args);
    }

}

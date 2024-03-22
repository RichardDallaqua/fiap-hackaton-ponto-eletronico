package com.fiap.ponto;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fiap.ponto")
@EnableRabbit
public class PontoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PontoApplication.class, args);
    }

}

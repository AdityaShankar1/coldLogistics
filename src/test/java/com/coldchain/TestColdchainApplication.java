package com.example.coldchain;

import org.springframework.boot.SpringApplication;

public class TestColdchainApplication {

    public static void main(String[] args) {
        SpringApplication.from(ColdchainApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}

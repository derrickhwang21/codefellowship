package com.hwang.secure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


    @SpringBootApplication
    public class SecureApplication {

        public static void main(String[] args) {
            SpringApplication.run(SecureApplication.class, args );
            System.out.println("http://localhost:8080");



        }

    }


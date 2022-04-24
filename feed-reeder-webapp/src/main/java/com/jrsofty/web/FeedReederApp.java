package com.jrsofty.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.jrsofty.web" })
public class FeedReederApp {
    public static void main(String[] args) {
        SpringApplication.run(FeedReederApp.class, args);
    }
}

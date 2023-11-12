package com.springstudy;

import com.springstudy.controllers.ServiceController;
import com.springstudy.models.Card;
import com.springstudy.models.Service;
import com.springstudy.repositories.ServiceRepository;
import com.springstudy.utils.ServiceUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}

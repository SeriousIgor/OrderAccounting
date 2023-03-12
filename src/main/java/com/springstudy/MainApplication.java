package com.springstudy;

import com.springstudy.dao.ClientDao;
import com.springstudy.dao.implementation.ClientDaoImplementation;
import com.springstudy.services.iService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.reflect.InvocationTargetException;

@SpringBootApplication
@EnableScheduling
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }
}

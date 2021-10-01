package com.example.demo;

import com.example.demo.models.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class DemoApplication {
    @Bean
    public static RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        Communication communication = new Communication(getRestTemplate());
        User user = new User(3L,"James","Brown", (byte)26);
        User user2 = new User(3L, "Thomas","Shelby",(byte)26);
        communication.AllUsers();
        communication.saveUser(user);
        communication.updateUser(user2);
        communication.deleteUser(user2);
    }
}
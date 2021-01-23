package com.jib.example.springboot.controller;

import com.jib.example.springboot.util.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Controller
public class SimpleController {
    private static final String JOKES_END_POINT = "https://official-joke-api.appspot.com/random_joke";

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    private RestTemplate createRestTemplate(RestTemplateBuilder builder) {
        return builder.setReadTimeout(Duration.ofSeconds(60))
                .errorHandler(new ExceptionHandler())
                .build();
    }

    @Bean
    @EventListener(SimpleController.class)
    private ResponseEntity<String> getRandomJoke() {
        System.out.println("Calling external Rest End Point");
        ResponseEntity<String> response = restTemplate.getForEntity(JOKES_END_POINT, String.class);
        System.out.println("Response body " + response.getBody());
        return response;
    }

}

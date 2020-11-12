package com.jib.example.spring;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

    @RestController
    public class Controller {
        @RequestMapping("/")
        public String message() {
            return "Hello From Spring-Boot Jib";
        }
    }


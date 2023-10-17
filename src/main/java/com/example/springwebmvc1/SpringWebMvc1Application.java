package com.example.springwebmvc1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringWebMvc1Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebMvc1Application.class, args);
    }

}

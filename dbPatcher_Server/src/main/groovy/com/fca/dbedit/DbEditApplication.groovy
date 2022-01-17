package com.fca.dbedit

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

/**
 * Created by kkulathilake on 5/10/2016.
 */

@SpringBootApplication
@EnableAutoConfiguration
class DbEditApplication {
    public static String ROOT = "upload-dir";

//    @Bean  - did not work!!! -> instead @CrossOrigin(origins = "*") added in DbEditController and com.fca.dbedit.config.RestConfiguration to configure REST server
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurerAdapter() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**").allowedOrigins("*");
//                registry.addMapping("/global/**").allowedOrigins("*");
//            }
//        };
//    }

    static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DbEditApplication.class, args);
    }
}

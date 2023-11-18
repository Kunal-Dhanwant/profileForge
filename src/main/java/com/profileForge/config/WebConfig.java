package com.profileForge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig  {

    @Bean
   public WebMvcConfigurer corsConfigur(){

       return new WebMvcConfigurer(){

           @Override
           public void addCorsMappings(CorsRegistry corsRegistry){
               corsRegistry.addMapping("/**");
           }

       };
   }
}
package com.example.authentication.spring.boots.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
    
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ui.url")
public class UiUrlPropertyConfig {
     private String home;
}

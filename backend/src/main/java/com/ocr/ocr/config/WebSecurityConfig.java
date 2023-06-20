/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ocr.ocr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.StaticHeadersWriter;

/**
 *
 * @author Diego
 */
@Configuration
public class WebSecurityConfig {
    
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().addHeaderWriter(new StaticHeadersWriter("Content-Security-Policy", "default-src 'self' 'unsafe-inline'"))
//                .addHeaderWriter(new StaticHeadersWriter("X-Content-Security-Policy", "default-src 'self' 'unsafe-inline'"))
//                .addHeaderWriter(new StaticHeadersWriter("X-WebKit-CSP", "default-src 'self' 'unsafe-inline'"))
                .and()
                .build();
        
    }
}

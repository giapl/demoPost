package com.example.demopost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public SecurityFilterChain filterChain (HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(new AntPathRequestMatcher("/api/v1/homes/**",HttpMethod.GET.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/postTopics/**",HttpMethod.POST.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/postTopics/**",HttpMethod.PUT.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/postTopics/**",HttpMethod.DELETE.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/questions/**",HttpMethod.GET.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/questions/**",HttpMethod.POST.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/questions/**",HttpMethod.PUT.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/questions/**",HttpMethod.DELETE.name())).permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/comments/**",HttpMethod.POST.name())).permitAll()
            .anyRequest()
            .denyAll())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }
}

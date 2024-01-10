package com.example.demopost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(requests -> requests
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/auth/register", HttpMethod.POST.name()))
            .permitAll()
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/auth/login", HttpMethod.POST.name()))
            .permitAll()
            .requestMatchers(new AntPathRequestMatcher("/api/v1/homes/**", HttpMethod.GET.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/postTopics/**", HttpMethod.POST.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/postTopics/**", HttpMethod.PUT.name()))
            .hasAuthority("USER")
            .requestMatchers(new AntPathRequestMatcher("/api/v1/postTopics/**",HttpMethod.GET.name())).permitAll()
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/postTopics/**", HttpMethod.DELETE.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/questions/**", HttpMethod.GET.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/questions/**", HttpMethod.POST.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/questions/**", HttpMethod.PUT.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/questions/**", HttpMethod.DELETE.name()))
            .hasAuthority("USER")
            .requestMatchers(
                new AntPathRequestMatcher("/api/v1/comments/**", HttpMethod.POST.name()))
            .hasAuthority("USER")
            .anyRequest()
            .denyAll())
        .httpBasic(Customizer.withDefaults());
    return http.build();
  }

}

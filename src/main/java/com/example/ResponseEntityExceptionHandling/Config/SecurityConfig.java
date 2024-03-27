package com.example.ResponseEntityExceptionHandling.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public JdbcUserDetailsManager userDetailsManager(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/springsecurity?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select user, pwd, enabled from users where user=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user, rol from roles where user=?");
        return jdbcUserDetailsManager;
    }

    //For encrypted passwords
    /**
    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .csrf(cus ->cus.disable())
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.POST, "/pets").hasRole("ADMIN")
                                .requestMatchers(HttpMethod.DELETE, "/pets/**").hasRole("ADMIN")
                                .requestMatchers("/pets").authenticated()
                                .requestMatchers("/pet/**").authenticated()
                                .anyRequest().permitAll())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }
}

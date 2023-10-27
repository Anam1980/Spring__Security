package com.example.Spring_Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/public/**")
                .permitAll()
                .requestMatchers("student/**")
                .hasAnyRole("STUDENT","ADMIN")
                .requestMatchers("/admin/**")
                .hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails student = User.withUsername("Tony")
                .password(getbCryptPasswordEncoder().encode("tony123"))//encrypt it
                .roles("STUDENT")
                .build();

        UserDetails admin = User.withUsername("Anam")
                .password(getbCryptPasswordEncoder().encode("anam100"))
                .roles("ADMIN")
                .build();

        //storing this details in Inmemory i.e. RAM
        //InMemoryUserDetailsManager is subclass of UserDetailService class
        return new InMemoryUserDetailsManager(student, admin);
    }

    //Encryption
    @Bean
    public BCryptPasswordEncoder getbCryptPasswordEncoder(){
        return  new BCryptPasswordEncoder();
    }
}

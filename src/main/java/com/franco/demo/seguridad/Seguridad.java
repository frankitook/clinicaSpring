package com.franco.demo.seguridad;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Seguridad extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Aquí puedes configurar la autenticación, por ejemplo, desde una base de datos
        auth.inMemoryAuthentication()
            .withUser("franco")
            .password(passwordEncoder().encode("123"))
            .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Aquí puedes configurar la autorización y la seguridad en las rutas
        http.authorizeRequests()
            .antMatchers("/public", "/home/registrar", "/home/ingresar").permitAll()
            .anyRequest().authenticated()
            .and()
            .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


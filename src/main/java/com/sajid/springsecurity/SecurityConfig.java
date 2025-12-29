package com.sajid.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filer(HttpSecurity http) throws Exception {

        http.
                authorizeHttpRequests(authorizeRequests -> authorizeRequests
                .requestMatchers("/assets/**").permitAll()
                .requestMatchers("/registration", "/login").permitAll()
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                )

                .rememberMe(rm-> rm
                        .rememberMeParameter("rememberMe")
                        .tokenValiditySeconds(10800)
                )
                .logout(lg -> lg
                        .logoutUrl("/logout")
                        .deleteCookies("JSESSIONID",  "remember-me")
                        .logoutSuccessUrl("/login")

                )
                .csrf(csrf -> csrf.disable())
        ;
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> User.withUsername(username).password(" ").build();
    }
}

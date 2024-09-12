package com.example.securityconquer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) // 모든 설정 코드는 람다 형식으로 작성해야함. (스프링 시큐리티 7버전 부터는 람다만 지원할 예정)
                .formLogin(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final UserDetails user1 = User.withUsername("user1")
                .password("{noop}1111")
                .roles("USER").build();

        final UserDetails user2 = User.withUsername("user2")
                .password("{noop}1111")
                .roles("USER").build();

        final UserDetails user3 = User.withUsername("user3")
                .password("{noop}1111")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user1, user2, user3);
    }
}

package com.example.securityconquer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .formLogin(setupFormLogin());

        return http.build();
    }

    private Customizer<FormLoginConfigurer<HttpSecurity>> setupFormLogin() {
        return form -> form
//                .loginPage("/loginPage") // 사용자 정의 로그인 페이지로 전환, 기본 로그인페이지 무시
                .loginProcessingUrl("/loginProc") // 사용자 이름과 비밀번호를 검증할 URL 지정 (Form 태그의 action 속성과 일치)
                .defaultSuccessUrl("/", false) // 로그인 성공 이후 이동 페이지, alwaysUse 가 true 이면 무조건 지정된 위치로 이동(기본은 false)
                .failureUrl("/loginFailed") //  인증에 실패할 경우 사용자에게 보내질 URL 을 지정, 기본값은 "/login?error" 이다
                .usernameParameter("userId") // 인증을 수행할 때 사용자 이름(아이디)을 찾기 위해 확인하는 HTTP 매개변수 설정, 기본값은 'username'. input 태그와 일치
                .passwordParameter("passwd") // 인증을 수행할 때 비밀번호를 찾기 위해 확인하는 HTTP 매개변수 설정, 기본값은 'password'
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws IOException {
                        System.out.println("authentication = " + authentication);
                        response.sendRedirect("/home");
                    }
                })
                .failureHandler((request, response, exception) -> {
                    System.out.println("exception = " + exception.getMessage());
                    response.sendRedirect("/login");
                })
                .permitAll();  // failureUrl(), loginPage(), loginProcessingUrl() 에 대한 URL은 인증을 받지 못해도 접근해야하기 때문에 설정.
    }

    @Bean
    public UserDetailsService userDetailsService() {
        final UserDetails user = User.withUsername("user")
                .password("{noop}1111")
                .roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }
}

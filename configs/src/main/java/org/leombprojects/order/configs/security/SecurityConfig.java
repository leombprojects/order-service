package org.leombprojects.order.configs.security;

import lombok.RequiredArgsConstructor;
import org.leombprojects.order.configs.security.error.AuthenticationExceptionHandler;
import org.leombprojects.order.configs.security.error.AuthorizationExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    @Autowired
    private AuthorizationExceptionHandler authorizationExceptionHandler;
    @Autowired private AuthenticationExceptionHandler authenticationExceptionHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.headers().httpStrictTransportSecurity().disable();
        http.cors().disable();
        http.csrf().disable();

        http.authorizeHttpRequests()
                .requestMatchers("/health/check").permitAll()
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt()
                .and()
                .authenticationEntryPoint(authenticationExceptionHandler)
                .accessDeniedHandler(authorizationExceptionHandler);
       return http.build();
   }
}
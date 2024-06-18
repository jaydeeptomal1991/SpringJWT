package com.springBootJWT.SpringJwt_Project.config;

import com.springBootJWT.SpringJwt_Project.filter.JwtAuthenticationFilter;
import com.springBootJWT.SpringJwt_Project.service.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.authorizeHttpRequests(
    			requests->
    			requests
                .requestMatchers("/login/**").permitAll()
                .requestMatchers("/registerUser/**").permitAll()
                .requestMatchers("/registerVendor/**").permitAll()
                .requestMatchers("/findUserForgetPassword/**").permitAll()
                .anyRequest().authenticated()
    			);
    	http.csrf(csrf->csrf.disable());
    	http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    	http.exceptionHandling(exception->exception.authenticationEntryPoint(unauthorizedHandler));
    	return http.build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    protected AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)throws Exception{
        return configuration.getAuthenticationManager();
    }
}

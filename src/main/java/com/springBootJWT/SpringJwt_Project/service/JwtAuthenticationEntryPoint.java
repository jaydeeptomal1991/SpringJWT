package com.springBootJWT.SpringJwt_Project.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER= LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOGGER.error("::::::ERROR::::::"+authException.getMessage());
        
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        
        final Map<String,Object>mapper=new HashMap<>();
        mapper.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        mapper.put("error", "Unauthorized");
        mapper.put("message", authException.getMessage());
        mapper.put("path", request.getServletPath());
        
        final ObjectMapper objectMapper=new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), mapper);
    }
}

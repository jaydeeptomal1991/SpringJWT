package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.JwtAuthResponse;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;
    @Autowired
    private  JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthenticationService.class);

    public ResponseMessage register(User request) {
        try {
            Optional<User> ifUserExists=userRepository.findByUsername(request.getUsername());
            if(ifUserExists.isPresent()){
                return new ResponseMessage(HttpStatus.CONFLICT.value(), "USER ALREADY PRESENT", null, null);
            }
            Date now=new Date();
            User user = new User();
            user.setCreatedDate(now);
            user.setName(request.getName());
            user.setPhoneNumber(request.getPhoneNumber());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setUsername(request.getUsername());
            user.setRole(request.getRole());
            user.setPendingRequest(true);
            User savedUser = userRepository.save(user);
            return new ResponseMessage(HttpStatus.ACCEPTED.value(), "SUCCESS::PENDING APPROVAL", null, savedUser);
        }catch(Exception ex){
            LOGGER.error(ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAIL::FAILED REGISTRATION", null, null);
        }
    }

    public JwtAuthResponse authenticate(User request) {
        User user = null;
        String token = null;
        try {
            Authentication obj = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            if (obj.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(obj);
                user = userRepository.findByUsername(request.getUsername()).get();
                LOGGER.info(":::::USER REQUEST IS:::::"+user.isPendingRequest());
                if(user.isPendingRequest()){
                    JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
                    jwtAuthResponse.setStatusCode(HttpStatus.LOCKED.value());
                    jwtAuthResponse.setMessage("PENDING ADMIN APPROVAL");
                    return jwtAuthResponse;
                }
                token = jwtService.generateToken(user);


            }
        } catch (Exception ex) {
            JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
            jwtAuthResponse.setStatusCode(HttpStatus.UNAUTHORIZED.value());
            jwtAuthResponse.setName("LOGIN FAILED");
            return jwtAuthResponse;
        }
        JwtAuthResponse jwtAuthResponse=new JwtAuthResponse();
        jwtAuthResponse.setStatusCode(HttpStatus.ACCEPTED.value());
        jwtAuthResponse.setName(user.getName());
        jwtAuthResponse.setUsername(user.getUsername());
        jwtAuthResponse.setRole(user.getRole().name());
        jwtAuthResponse.setEnabled(true);
        jwtAuthResponse.setMessage("ACCEPTED");
        jwtAuthResponse.setToken(token);
        return jwtAuthResponse;

    }
}

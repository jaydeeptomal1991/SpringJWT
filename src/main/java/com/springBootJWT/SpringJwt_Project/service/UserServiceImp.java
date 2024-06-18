package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.UserDto;
import com.springBootJWT.SpringJwt_Project.dto.UserRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImp.class);

    @Override
    public ResponseMessage showUserList(String role, String name) {
        try {
            List<User> list = userRepository.showUsersLists(role, name);
            return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS", null, list);
        } catch (Exception ex) {
            LOGGER.debug("Exception is::::: " + ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAILED", null, null);
        }
    }

    @Override
    public ResponseMessage updateUserData(UserRequestDTO userRequestDTO) {
        try {
            System.out.println(userRequestDTO);
            Optional<User> user = userRepository.findById(userRequestDTO.getUserId());
            if (user.isEmpty()) {
                return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "USER NOT FOUND", null, null);
            } else {
                User getUser = user.get();
                getUser.setPendingRequest(userRequestDTO.isPendingRequest());
                User data = userRepository.save(getUser);
                return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS", null, data);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.debug("Exception is::::: " + ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAILED", null, null);
        }
    }

    @Override
    public ResponseMessage forgetPassword(UserDto user) {
        try {
            String username = user.getUsername();
            Optional<User> ifUserPresent = userRepository.findByUsername(username);
            if(ifUserPresent.isEmpty()){
                return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "User not found");
            }else{
                User getUser=ifUserPresent.get();
                try {
                    return new ResponseMessage(HttpStatus.OK.value(), "Email sent to registered email Id");
                }catch(Exception ex){
                    ex.printStackTrace();
                    return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Error sending email");
                }

            }
        } catch (Exception ex) {
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "Error");
        }
    }
}

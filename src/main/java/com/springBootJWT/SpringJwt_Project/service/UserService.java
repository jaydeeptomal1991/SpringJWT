package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.UserDto;
import com.springBootJWT.SpringJwt_Project.dto.UserRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;
import com.springBootJWT.SpringJwt_Project.model.User;

public interface UserService {

    public ResponseMessage showUserList(String role,String name);

    public ResponseMessage updateUserData(UserRequestDTO userRequestDTO);

    public ResponseMessage forgetPassword(UserDto user);
}

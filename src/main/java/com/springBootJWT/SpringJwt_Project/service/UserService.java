package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.UserRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;

public interface UserService {

    public ResponseMessage showUserList(String role,String name);

    public ResponseMessage updateUserData(UserRequestDTO userRequestDTO);
}

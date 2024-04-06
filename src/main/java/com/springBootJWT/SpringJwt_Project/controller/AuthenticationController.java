package com.springBootJWT.SpringJwt_Project.controller;

import com.springBootJWT.SpringJwt_Project.dto.UserRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.model.Vendor;
import com.springBootJWT.SpringJwt_Project.service.AuthenticationService;
import com.springBootJWT.SpringJwt_Project.service.CurrentUser;
import com.springBootJWT.SpringJwt_Project.service.UserServiceImp;
import com.springBootJWT.SpringJwt_Project.service.VendorServiceImp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final VendorServiceImp vendorServiceImp;

    private final UserServiceImp userServiceImp;

    public AuthenticationController(AuthenticationService authenticationService, VendorServiceImp vendorServiceImp, UserServiceImp userServiceImp) {
        this.authenticationService = authenticationService;
        this.vendorServiceImp = vendorServiceImp;
        this.userServiceImp = userServiceImp;
    }

    @PostMapping("/registerUser")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        return ResponseEntity.ok(authenticationService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user){
        return ResponseEntity.ok(authenticationService.authenticate(user));
    }

    @PostMapping("/registerVendor")
    public ResponseEntity<?> registerVendor(@RequestBody Vendor vendor, @CurrentUser User user){
        return ResponseEntity.ok(vendorServiceImp.registerVendor(vendor,user));
    }

    @GetMapping("/showUserList")
    private ResponseEntity<?> showUserList(@CurrentUser User user){
        return ResponseEntity.ok(userServiceImp.showUserList("USER","ADMIN"));
    }

    @PatchMapping("/updateUser")
    private ResponseEntity<?> updateUserData(@CurrentUser User user,@RequestBody UserRequestDTO userRequestDTO){
        return ResponseEntity.ok(userServiceImp.updateUserData(userRequestDTO));
    }

}

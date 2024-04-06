package com.springBootJWT.SpringJwt_Project.controller;

import com.springBootJWT.SpringJwt_Project.dto.VendorRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.model.Vendor;
import com.springBootJWT.SpringJwt_Project.service.CurrentUser;
import com.springBootJWT.SpringJwt_Project.service.VendorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class VendorController {

    private VendorServiceImp vendorServiceImp;

    public VendorController(VendorServiceImp vendorServiceImp) {
        this.vendorServiceImp = vendorServiceImp;
    }

    @PostMapping("/register")
    private ResponseEntity<?> registerVendor(@RequestBody Vendor vendor, @CurrentUser User user){
        return ResponseEntity.ok(vendorServiceImp.registerVendor(vendor,user));
    }

    @GetMapping("/showVendorList")
    private ResponseEntity<?> showVendorList(@CurrentUser User user){
        System.out.println(":::::::::ROLE IS::::::::::"+user.getRole().name());
        return ResponseEntity.ok(vendorServiceImp.showVendorList());
    }

    @PatchMapping("/updateVendor")
    private ResponseEntity<?> updateVendor(@CurrentUser User user,@RequestBody VendorRequestDTO vendorRequestDTO){
        return ResponseEntity.ok(vendorServiceImp.updateVendor(vendorRequestDTO));
    }


}

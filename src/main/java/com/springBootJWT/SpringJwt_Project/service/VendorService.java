package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.VendorRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.model.Vendor;

import java.util.List;

public interface VendorService {

     ResponseMessage registerVendor(Vendor vendor,User user);

     ResponseMessage showVendorList();

     ResponseMessage updateVendor(VendorRequestDTO vendorRequestDTO);
}

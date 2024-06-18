package com.springBootJWT.SpringJwt_Project.service;

import com.springBootJWT.SpringJwt_Project.dto.CountResponse;
import com.springBootJWT.SpringJwt_Project.dto.VendorRequestDTO;
import com.springBootJWT.SpringJwt_Project.model.ResponseMessage;
import com.springBootJWT.SpringJwt_Project.model.User;
import com.springBootJWT.SpringJwt_Project.model.Vendor;
import com.springBootJWT.SpringJwt_Project.repository.UserRepository;
import com.springBootJWT.SpringJwt_Project.repository.VendorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VendorServiceImp implements VendorService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VendorRepository vendorRepository;


    private static final Logger LOGGER = LoggerFactory.getLogger(VendorServiceImp.class);

    @Override
    public ResponseMessage registerVendor(Vendor vendor, User user1) {
        try {
            boolean ifVendorExists = vendorRepository.existsByVendorUsername(vendor.getVendorUsername());
            if (ifVendorExists) {
                return new ResponseMessage(HttpStatus.CONFLICT.value(), "VENDOR EXISTS", null, null);
            }
            Date now = new Date();
            vendor.setCreatedDate(now);
            User user = new User();
            user.setCreatedDate(now);
            user.setPassword(passwordEncoder.encode(vendor.getPhoneNumber()));
            user.setRole(vendor.getRole());
            user.setName(vendor.getVendorName());
            user.setPhoneNumber(vendor.getPhoneNumber());
            if (user1 != null) {
                if (user1.getRole().name().equals("ADMIN")) {
                    user.setPendingRequest(false);
                } else {
                    user.setPendingRequest(true);
                }
            } else {
                user.setPendingRequest(true);
            }

            user.setUsername(vendor.getVendorUsername());
            userRepository.save(user);
            vendor.setEnabled(true);
            if (user1 != null) {
                if (user1.getRole().name().equals("ADMIN")) {
                    vendor.setPendingRequest(false);
                } else {
                    vendor.setPendingRequest(true);
                }
            } else {
                vendor.setPendingRequest(true);
            }
            vendor.setPasswordChangedFirst(false);
            Vendor obj = vendorRepository.save(vendor);
            if (user1 != null) {
                if (user1.getRole().name().equals("ADMIN")) {
                    return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS", null, obj);
                } else {
                    return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS::PENDING APPROVAL\nPASSWORD:: PHONE NUMBER", null, obj);
                }
            }
            return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS::PENDING APPROVAL\nPASSWORD:: PHONE NUMBER", null, obj);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAIL::FAILED REGISTRATION", null, null);
        }
    }

    @Override
    public ResponseMessage showVendorList() {
        try {
            List<Vendor> list = vendorRepository.findAll();
            return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS", null, list);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAILED", null, null);
        }
    }

    @Override
    public ResponseMessage updateVendor(VendorRequestDTO vendorRequestDTO) {
        try {
            Optional<Vendor> getVendor = vendorRepository.findById(vendorRequestDTO.getVendorId());
            if (getVendor.isPresent()) {
                Vendor obj = getVendor.get();
                obj.setPendingRequest(vendorRequestDTO.isPendingRequest());
                Vendor vendor = vendorRepository.save(obj);
                Optional<User> user = userRepository.findByUsername(vendor.getVendorUsername());
                if(user.isPresent()) {
                    User userObj=user.get();
                    userObj.setPendingRequest(vendorRequestDTO.isPendingRequest());
                    userRepository.save(userObj);
                }
                return showVendorList();
            } else {
                LOGGER.info(":::::::::::::::Vendor not Found:::::::::::::::::");
                return new ResponseMessage(HttpStatus.NOT_FOUND.value(), "VENDOR NOT FOUND", null, null);
            }
        } catch (Exception ex) {
            LOGGER.error(":::::::::::::::Update Error:::::::::::::::::" + ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAILED", null, null);
        }
    }


    @Override
    public ResponseMessage showVendorCount() {
        try {
//            Vendor Count
            Integer showVendorTotalCount = vendorRepository.findAll().size();
            Integer showVendorPendingRequestTrue = vendorRepository.showVendorCountPendingRequestTrue();
            Integer showVendorPendingRequestFalse = vendorRepository.showVendorCountPendingRequestFalse();

//          User Count

            Integer showUserTotalCount = userRepository.showUserCount();
            Integer showUserPendingRequestTrue = userRepository.showUserCountPendingRequestTrue();
            Integer showUserPendingRequestFalse = userRepository.showUserCountPendingRequestFalse();

            return new ResponseMessage(HttpStatus.OK.value(), "SUCCESS",null,
                    new CountResponse(showVendorTotalCount,showVendorPendingRequestTrue,
                            showVendorPendingRequestFalse,showUserTotalCount,showUserPendingRequestTrue,showUserPendingRequestFalse));
        }catch (Exception ex){
            LOGGER.error(":::::::::::::::Update Error:::::::::::::::::" + ex.getMessage());
            return new ResponseMessage(HttpStatus.BAD_REQUEST.value(), "FAILED", null, null);
        }
    }
}

package com.springBootJWT.SpringJwt_Project.repository;

import com.springBootJWT.SpringJwt_Project.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
    boolean existsByVendorUsername(String vendorUsername);
}

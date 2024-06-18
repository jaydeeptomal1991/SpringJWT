package com.springBootJWT.SpringJwt_Project.repository;

import com.springBootJWT.SpringJwt_Project.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VendorRepository extends JpaRepository<Vendor,Integer> {
    boolean existsByVendorUsername(String vendorUsername);

    @Query(value = "select count(*) FROM vendor WHERE pending_request=true",nativeQuery = true)
    Integer showVendorCountPendingRequestTrue();

    @Query(value = "select count(*) FROM vendor WHERE pending_request=false",nativeQuery = true)
    Integer showVendorCountPendingRequestFalse();
}

package com.springBootJWT.SpringJwt_Project.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vendor")
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer vendorId;
    private String vendorName;
    private String vendorLicenseOwner;
    private String registrationNumber;
    private String gstNumber;
    private String vendorAddress;
    private String state;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private String vendorUsername;
    private Date createdDate;
    private Date updatedDate;
    private boolean isEnabled;
    @Enumerated(EnumType.STRING)
    private Role role;
    private boolean isPasswordChangedFirst;

    private boolean pendingRequest;

    public boolean isPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(boolean pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String      getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getVendorAddress() {
        return vendorAddress;
    }

    public void setVendorAddress(String vendorAddress) {
        this.vendorAddress = vendorAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getVendorUsername() {
        return vendorUsername;
    }

    public void setVendorUsername(String vendorUsername) {
        this.vendorUsername = vendorUsername;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public String getVendorLicenseOwner() {
        return vendorLicenseOwner;
    }

    public void setVendorLicenseOwner(String vendorLicenseOwner) {
        this.vendorLicenseOwner = vendorLicenseOwner;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public boolean isPasswordChangedFirst() {
        return isPasswordChangedFirst;
    }

    public void setPasswordChangedFirst(boolean passwordChangedFirst) {
        isPasswordChangedFirst = passwordChangedFirst;
    }
}

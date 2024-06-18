package com.springBootJWT.SpringJwt_Project.dto;

public class CountResponse {
    private Integer vendorTotalCount;
    private Integer vendorPendingRequestTrueCount;
    private Integer vendorPendingRequestFalseCount;

    private Integer userTotalCount;
    private Integer userPendingRequestTrueCount;
    private Integer userPendingRequestFalseCount;


    public CountResponse(Integer vendorTotalCount, Integer vendorPendingRequestTrueCount, Integer vendorPendingRequestFalseCount, Integer userTotalCount, Integer userPendingRequestTrueCount, Integer userPendingRequestFalseCount) {
        this.vendorTotalCount = vendorTotalCount;
        this.vendorPendingRequestTrueCount = vendorPendingRequestTrueCount;
        this.vendorPendingRequestFalseCount = vendorPendingRequestFalseCount;
        this.userTotalCount = userTotalCount;
        this.userPendingRequestTrueCount = userPendingRequestTrueCount;
        this.userPendingRequestFalseCount = userPendingRequestFalseCount;
    }

    public CountResponse() {
    }

    public Integer getVendorTotalCount() {
        return vendorTotalCount;
    }

    public void setVendorTotalCount(Integer vendorTotalCount) {
        this.vendorTotalCount = vendorTotalCount;
    }

    public Integer getVendorPendingRequestTrueCount() {
        return vendorPendingRequestTrueCount;
    }

    public void setVendorPendingRequestTrueCount(Integer vendorPendingRequestTrueCount) {
        this.vendorPendingRequestTrueCount = vendorPendingRequestTrueCount;
    }

    public Integer getVendorPendingRequestFalseCount() {
        return vendorPendingRequestFalseCount;
    }

    public void setVendorPendingRequestFalseCount(Integer vendorPendingRequestFalseCount) {
        this.vendorPendingRequestFalseCount = vendorPendingRequestFalseCount;
    }

    public Integer getUserTotalCount() {
        return userTotalCount;
    }

    public void setUserTotalCount(Integer userTotalCount) {
        this.userTotalCount = userTotalCount;
    }

    public Integer getUserPendingRequestTrueCount() {
        return userPendingRequestTrueCount;
    }

    public void setUserPendingRequestTrueCount(Integer userPendingRequestTrueCount) {
        this.userPendingRequestTrueCount = userPendingRequestTrueCount;
    }

    public Integer getUserPendingRequestFalseCount() {
        return userPendingRequestFalseCount;
    }

    public void setUserPendingRequestFalseCount(Integer userPendingRequestFalseCount) {
        this.userPendingRequestFalseCount = userPendingRequestFalseCount;
    }
}
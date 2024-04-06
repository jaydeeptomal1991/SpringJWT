package com.springBootJWT.SpringJwt_Project.dto;


public class VendorRequestDTO {
    private Integer vendorId;
    private boolean pendingRequest;

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public boolean isPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(boolean pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    @Override
    public String toString() {
        return "VendorRequestDTO{" +
                "vendorId=" + vendorId +
                ", pendingRequest=" + pendingRequest +
                '}';
    }
}

package com.springBootJWT.SpringJwt_Project.dto;

public class UserRequestDTO {
    private Integer userId;
    private boolean pendingRequest;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isPendingRequest() {
        return pendingRequest;
    }

    public void setPendingRequest(boolean pendingRequest) {
        this.pendingRequest = pendingRequest;
    }

    @Override
    public String toString() {
        return "UserRequestDTO{" +
                "userId=" + userId +
                ", pendingRequest=" + pendingRequest +
                '}';
    }
}

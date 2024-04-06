package com.springBootJWT.SpringJwt_Project.model;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private int statusCode;
    private String message;
    private String token;
    private Object data;



}

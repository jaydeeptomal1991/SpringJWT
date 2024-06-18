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


    public ResponseMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public ResponseMessage(int statusCode, String message, Object data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
    }

	public ResponseMessage(int statusCode, String message, String token, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.token = token;
		this.data = data;
	}
    
    
}

package com.ibm.fsd.jwt;

import lombok.Data;

@Data
public class JwtRequest {
	
	private String email;
	private String username;
    private String password;
    
    public JwtRequest() {}
    public JwtRequest(String email, String username, String password) {
    	this.email = email;
        this.username = username;
        this.password = password;
    }
}

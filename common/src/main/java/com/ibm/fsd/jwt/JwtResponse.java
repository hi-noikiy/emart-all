package com.ibm.fsd.jwt;

import lombok.Data;

@Data
public class JwtResponse {
	
	private int id;
	private String email;
    private String username;
    private String roles;
    private String token;

    public JwtResponse(String token, int id, String email, String username, String roles) {
    	this.id = id;
    	this.email = email;
        this.username = username;
        this.roles = roles;
        this.token = token;
    }
}

package com.ibm.fsd.dto;


import lombok.Data;

@Data
public class UserDto {
	
	private int id;
	private String username;
	private String password;
	private String email;
	private String roles;
    private String confirmPassword;
    private String mobileNumber;
    private String contactNumber;
    private String companyName;
    private String briefAboutCompany;
    private String postalAddress;

}

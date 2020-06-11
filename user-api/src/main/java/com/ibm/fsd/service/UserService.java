package com.ibm.fsd.service;

import com.ibm.fsd.dto.UserDto;

/**
 * user Info
 */
public interface UserService {
	
	/** login */
	UserDto login(String emali, String password);
	
	/** signup */
	UserDto signup(UserDto dto);
	
	/** password update */
	int password(UserDto dto);
	
	UserDto findByEmail(String emali);
}

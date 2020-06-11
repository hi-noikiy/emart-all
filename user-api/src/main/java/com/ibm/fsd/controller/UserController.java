package com.ibm.fsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.ibm.fsd.dto.UserDto;
import com.ibm.fsd.exception.ErrorConstants;
import com.ibm.fsd.exception.RequestException;
import com.ibm.fsd.jwt.JwtResponse;
import com.ibm.fsd.service.JwtService;
import com.ibm.fsd.service.SecurityService;
import com.ibm.fsd.service.UserService;

/**
 * user controller
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	JwtService jwtService;

	@Autowired
	SecurityService securityService;

	/**
	 * login
	 * 
	 * @param form
	 * @return token
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<JwtResponse> login(@RequestBody UserDto dto) {

		// JWT Authentication
		UserDetails userDetails = null;
		try {
			userDetails = jwtService.authenticate(dto.getUsername(), dto.getPassword());
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

		// set token
		final String token = jwtService.generateToken(userDetails);

		// get user info
		UserDto userDto = userService.login(dto.getUsername(), dto.getPassword());

		// result return
		return ResponseEntity.ok(
				new JwtResponse(token, userDto.getId(), userDto.getEmail(), userDto.getUsername(), userDto.getRoles()));
	}

	/**
	 * sign up
	 * 
	 * @param dto
	 */
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<UserDto> signup(@RequestBody UserDto dto,
			BindingResult bindingResult) {
		
		UserDto retDtoNotExists = userService.findByEmail(dto.getEmail());
		
		if (retDtoNotExists != null) {
			throw new RequestException(ErrorConstants.ERROR_REQUEST_PARAM, "the email is existed.", bindingResult);
        }
		return ResponseEntity.ok(userService.signup(dto));
	}
	
	/**
	 * password update
	 * 
	 * @param dto
	 */
	@RequestMapping(value = "/passwordUpdate", method = RequestMethod.POST)
	public ResponseEntity<Integer> password(@RequestBody UserDto dto,
			BindingResult bindingResult) {
		
		UserDto retDtoNotExists = userService.findByEmail(dto.getEmail());
		
		if (retDtoNotExists == null) {
 			throw new RequestException(ErrorConstants.ERROR_REQUEST_PARAM, "the email is not existed.", bindingResult);
        }
		return ResponseEntity.ok(userService.password(dto));
	}
}

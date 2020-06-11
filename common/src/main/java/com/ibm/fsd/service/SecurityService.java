package com.ibm.fsd.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.fsd.entity.Users;
import com.ibm.fsd.repository.JwtRepository;

/**
 * SecurityService
 */
@Service
public class SecurityService implements UserDetailsService {
	
	@Autowired
    JwtRepository jwtRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// get email
		Users user = jwtRepository.findByEmail(username);
		
		// null
		if(user == null){
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(user.getRoles()));
		
		// set username and password
		return new User(user.getEmail(), user.getPassword(), authorities);
	}

}

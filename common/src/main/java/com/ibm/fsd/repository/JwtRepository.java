package com.ibm.fsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.Users;


@Repository
public interface JwtRepository extends JpaRepository<Users, String> {
	
	/** get email */
	Users findByEmail(String email);
}

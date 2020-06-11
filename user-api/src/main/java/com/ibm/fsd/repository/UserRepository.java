package com.ibm.fsd.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.dto.UserDto;


/**
 * user Info
 */
@Repository
public interface UserRepository extends JwtRepository {
	
	@Modifying
	@Query(value = "update users set password = :#{#user.password}, confirm_password = :#{#user.confirmPassword} where email=:#{#user.email}", nativeQuery = true)
	int update(@Param("user") UserDto user);
	
}

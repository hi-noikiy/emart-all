package com.ibm.fsd.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibm.fsd.entity.Manufacturer;

/**
 * manufacturer repository
 */
@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, String> {
	
	/** find manufacturer code */
	Manufacturer findByManufacturerCode(String manufacturerCode);
}

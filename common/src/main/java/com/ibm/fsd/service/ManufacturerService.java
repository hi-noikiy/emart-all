package com.ibm.fsd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.entity.Manufacturer;
import com.ibm.fsd.repository.ManufacturerRepository;


/**
 * manufacturer service
 */
@Service
public class ManufacturerService {

	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	/**
	 * manufacturer find all
	 */
	public List<Manufacturer> findAll() {
		return manufacturerRepository.findAll();
	}
	
	/**
	 * manufacturer find one
	 */
	public Manufacturer findAll(String manufacturerCode) {
		return manufacturerRepository.findByManufacturerCode(manufacturerCode);
	} 
	
	/**
	 * manufacturer save
	 * 
	 * @param manufacturer
	 * @return manufacturer
	 */
    @Transactional
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }
}

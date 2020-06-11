package com.ibm.fsd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.fsd.entity.Evaluate;
import com.ibm.fsd.repository.EvaluateRepository;
import com.ibm.fsd.service.EvaluateService;

/**
 * 
 * Purchase History service
 *
 */
@Service
public class EvaluateServiceImpl implements EvaluateService {

	@Autowired
	EvaluateRepository evaluateRepository;

	@Override
	@Transactional
	public Evaluate save(Evaluate evaluate) {
		return evaluateRepository.save(evaluate);
	}

}

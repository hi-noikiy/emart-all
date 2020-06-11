package com.ibm.fsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ibm.fsd.entity.Evaluate;
import com.ibm.fsd.service.EvaluateService;


@CrossOrigin
@RestController
@RequestMapping("/api")
public class EvaluateController {
    
	@Autowired
	EvaluateService evaluateService;
	
	/**
	 * evaluate add
	 */
	@RequestMapping(value = "/buyer/addEvaluate", method = RequestMethod.POST)
	public ResponseEntity<Evaluate> evaluateAdd(@RequestBody Evaluate evaluate) {
		return ResponseEntity.ok(evaluateService.save(evaluate));
	}
}

package com.ibm.fsd.exception;

import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class RequestException extends RuntimeException {
	private String errorCode;
	private String errorMsg;
	private Errors errors;
	
	public RequestException(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	public RequestException(String errorCode, Errors errors) {
		this.errorCode = errorCode;
		this.errors = errors;
	}
	
	public RequestException(String errorCode, String errorMsg, Errors errors) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
		this.errors = errors;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public Errors getErrors() {
		return errors;
	}

}

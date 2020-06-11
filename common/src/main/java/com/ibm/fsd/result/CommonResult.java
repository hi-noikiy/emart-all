package com.ibm.fsd.result;

import java.util.List;

import com.ibm.fsd.exception.FieldErrorResource;

public class CommonResult<T> {
	private int status = 1;
	private String errorCode = "";
	private String errorMsg = "";
	private List<FieldErrorResource> fieldErrors;
	private T resultBody;
	
	public CommonResult() {
	}

	public CommonResult(T resultBody) {
		this.resultBody = resultBody;
	}
	
	public static <T> CommonResult<T> errorResult(String errorCode, String errorMsg){
	    CommonResult<T> commonResult = new CommonResult<>();
	    commonResult.errorCode = errorCode;
	    commonResult.errorMsg = errorMsg;
	    commonResult.status = -1;
	    return commonResult;
	}
	
	public static <T> CommonResult<T> errorResult(String errorCode, String errorMsg, List<FieldErrorResource> fieldErrors){
		CommonResult<T> commonResult = new CommonResult<>();
		commonResult.errorCode = errorCode;
		commonResult.errorMsg = errorMsg;
		commonResult.status = -1;
		commonResult.fieldErrors = fieldErrors;
		return commonResult;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public T getResultBody() {
		return resultBody;
	}

	public void setResultBody(T resultBody) {
		this.resultBody = resultBody;
	}

	public List<FieldErrorResource> getFieldErrors() {
		return fieldErrors;
	}

	public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
		this.fieldErrors = fieldErrors;
	}

}
  

package org.tikim.boot.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.tikim.boot.enums.ErrorMessage;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "cause", "stackTrace","message","localizedMessage","message","suppressed" })
public class BaseException extends RuntimeException{
	protected String className;
	protected String errorMessage;
	protected Integer code;
	protected String ErrorTrace;
	
	
	public String getErrorTrace() {
		return ErrorTrace;
	}
	public void setErrorTrace(String errorTrace) {
		ErrorTrace = errorTrace;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
    
    public BaseException(ErrorMessage errorMessage) {
    	className = this.getClass().getSimpleName();
        this.errorMessage = errorMessage.getErrorMessage();
        this.code = errorMessage.getCode();
    }
    public BaseException(String className, ErrorMessage errorMessage) {
    	this.className = className;
        this.errorMessage = errorMessage.getErrorMessage();
        this.code = errorMessage.getCode();
    }
}

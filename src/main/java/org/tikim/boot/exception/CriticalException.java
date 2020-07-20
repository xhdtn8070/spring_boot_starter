package org.tikim.boot.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.tikim.boot.enums.ErrorMessage;


public class CriticalException extends BaseException {

	public CriticalException(String className, ErrorMessage errorMessage) {
		super(className, errorMessage);
	}
	public CriticalException(ErrorMessage errorMessage) {
		super(errorMessage);
	}
}

package com.starking.vendas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@org.springframework.web.bind.annotation.ControllerAdvice
@RestControllerAdvice
public class ControllerAdvice {
	
	@ExceptionHandler(RegraNegocioException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ApiErrors handleRegraNegocioException(RegraNegocioException e) {
		String msg = e.getMessage();
		return new ApiErrors(msg);
	}

}

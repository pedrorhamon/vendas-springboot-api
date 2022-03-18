package com.starking.vendas.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;

@Getter
public class ApiErrors {
	
	private List<String> errors;
	
	public ApiErrors(String msg) {
		this.errors = Arrays.asList(msg);
	}
	
	public ApiErrors(List<String> errors) {
		this.errors = errors;
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ApiErrors handlePedidoFoundException(PedidoNaoEncontradoException msg) {
		return new ApiErrors(msg.getMessage());
	}

}

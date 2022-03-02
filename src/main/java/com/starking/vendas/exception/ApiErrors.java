package com.starking.vendas.exception;

import java.util.Arrays;
import java.util.List;

import lombok.Getter;

@Getter
public class ApiErrors {
	
	private List<String> errors;
	
	public ApiErrors(String msg) {
		this.errors = Arrays.asList(msg);
	}

}

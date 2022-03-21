package com.starking.vendas.services.validation.constraintvalidation;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.starking.vendas.services.validation.NotEmptyList;


public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{
	
	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		return value != null && !value.isEmpty();
	}
	
	public void initialize(NotEmptyList constraintAnnotation) {};
}

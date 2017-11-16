package com.ninos.web.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

public class MyTypeValidator implements ConstraintValidator<TypeValidator, String> {
	private String[] values;

	@Override
	public void initialize(TypeValidator annotation) {
		values = annotation.values();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return Optional.ofNullable(values)
		               .filter(v -> Arrays.asList(v).contains(value))
		               .isPresent();
	}
}

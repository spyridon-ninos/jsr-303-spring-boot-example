package com.ninos.web.model;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyTypeValidator.class)
public @interface TypeValidator {
	String[] values();
	String message() default "Invalid Type Value";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

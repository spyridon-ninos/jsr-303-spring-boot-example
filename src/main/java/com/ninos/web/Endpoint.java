package com.ninos.web;

import com.ninos.web.model.Request;
import com.ninos.web.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(
		consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class Endpoint {

	@PostMapping
	public Response consumeRequest(
			@RequestBody @Valid Request request
	) {
		return new Response();
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Response handleValidationErrors(MethodArgumentNotValidException e) {

		Response response = new Response();

		for (FieldError fieldError: e.getBindingResult().getFieldErrors()) {
			response.addError(fieldError.getField());
		}

		return response;
	}
}

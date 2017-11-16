package com.ninos.web.model;

import java.util.ArrayList;
import java.util.List;

public class Response {
	private List<String> errorList;
	private String message;

	public List<String> getErrorList() {
		if (errorList == null) {
			errorList = new ArrayList<>();
		}

		return errorList;
	}

	public void addError(String error) {
		getErrorList().add(error);
	}

	public void setErrorList(List<String> errorList) {
		this.errorList = errorList;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}

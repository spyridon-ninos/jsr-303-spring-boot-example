package com.ninos.web.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Request {

	@NotNull
	private Long id;

	@Pattern(regexp = "Spyros|Spyridon")
	private String name;

	@TypeValidator(values = { "Spyros", "Spyridon" })
	private String type;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

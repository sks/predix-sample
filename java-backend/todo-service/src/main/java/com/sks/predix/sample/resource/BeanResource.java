package com.sks.predix.sample.resource;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties({ "id" })
public class BeanResource extends ResourceSupport {

	@JsonUnwrapped
	private Object resorce;

	public BeanResource(Object resorce) {
		this.resorce = resorce;
	}

	public Object getResorce() {
		return resorce;
	}

}

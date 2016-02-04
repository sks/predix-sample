package com.sks.predix.sample.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.sks.predix.sample.controller.TodoController;
import com.sks.predix.sample.entity.Todo;

public class ResourceAssembler extends
		ResourceAssemblerSupport<Todo, BeanResource> {

	public ResourceAssembler() {
		super(TodoController.class, BeanResource.class);
	}

	@Override
	public BeanResource toResource(Todo entity) {
		BeanResource userResource = new BeanResource(entity);
		Link selfLink = linkTo(
				methodOn(TodoController.class).findOne(entity.getId()))
				.withSelfRel();
		userResource.add(selfLink);

		return userResource;
	}

}

package com.sks.predix.sample.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sks.predix.sample.entity.Todo;
import com.sks.predix.sample.resource.BeanResource;
import com.sks.predix.sample.resource.ResourceAssembler;
import com.sks.predix.sample.service.TodoService;

@RestController
@RequestMapping(value = "todo")
public class TodoController {

	private ResourceAssembler resourceAssembler = new ResourceAssembler();

	@Autowired
	private TodoService service;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BeanResource> create(@RequestBody @Valid Todo todo) {
		Todo savedEntity = this.service.create(todo);
		BeanResource beanResource = resourceAssembler.toResource(savedEntity);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(getLinkToEntity(savedEntity).toUri());
		return new ResponseEntity<BeanResource>(beanResource, headers,
				HttpStatus.CREATED);
	}

	private ControllerLinkBuilder getLinkToEntity(Todo savedEntity) {
		return linkTo(methodOn(getClass()).findOne(savedEntity.getId()));
	}

	@RequestMapping(method = RequestMethod.GET)
	public Resources<BeanResource> getAll() {
		Iterable<Todo> results = this.service.findAll();
		List<BeanResource> resources = resourceAssembler.toResources(results);
		return new Resources<BeanResource>(resources, linkTo(getClass())
				.withSelfRel());
	}

	@RequestMapping(method = { RequestMethod.PUT, RequestMethod.DELETE })
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public void methodsNotImplemented() {

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseStatus(code = HttpStatus.NOT_IMPLEMENTED)
	public void methodsNotImplemented(@PathVariable Long id) {
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public BeanResource findOne(@PathVariable Long id) {
		return resourceAssembler.toResource(this.service.findOne(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public BeanResource findOne(@PathVariable Long id,
			@RequestBody @Valid Todo t) {
		return resourceAssembler.toResource(this.service.update(id, t));
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		this.service.delete(id);
	}
}

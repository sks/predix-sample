package com.sks.predix.sample.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sks.predix.common.exception.CommonCloudException;
import com.sks.predix.sample.entity.Todo;
import com.sks.predix.sample.repository.TodoRepository;

@Service
public class TodoService {

	@Autowired
	TodoRepository repository;

	public Todo create(Todo t) {
		if (this.exists(t)) {
			throw new CommonCloudException(HttpStatus.CONFLICT.value());
		}
		return this.repository.save(t);

	}

	public boolean exists(Todo t) {
		return null != t.getId();
	}

	public Todo update(Long id, Todo t) {
		this.findOne(id);
		t.setId(id);
		return this.repository.save(t);
	}

	public Iterable<Todo> findAll() {
		return this.repository.findAll();
	}

	public Todo findOne(Long id) {
		if (!this.repository.exists(id)) {
			throw new EntityNotFoundException();
		}
		return this.repository.findOne(id);
	}

	public void delete(Long id) {
		this.findOne(id);
		this.repository.delete(id);
	}
}

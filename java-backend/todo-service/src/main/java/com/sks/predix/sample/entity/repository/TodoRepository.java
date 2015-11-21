package com.sks.predix.sample.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import com.sks.predix.sample.entity.Todo;

@RestResource(path = "/todo")
public interface TodoRepository extends CrudRepository<Todo, Long> {

}

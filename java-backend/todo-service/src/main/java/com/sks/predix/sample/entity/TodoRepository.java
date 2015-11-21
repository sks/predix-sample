package com.sks.predix.sample.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(path = "/todo")
public interface TodoRepository extends CrudRepository<Todo, Long> {

}

package com.sks.predix.sample.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sks.predix.sample.entity.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

	@Query("select m from Todo as m where m.owner = ?#{ principal }")
	@Override
	Iterable<Todo> findAll();

	@Query("select m from Todo as m where m.owner = ?#{ principal } and m.id=?1")
	@Override
	Todo findOne(Long id);

	@Modifying
	@Query("delete from Todo as m where m.id = ?1 and m.owner = ?#{ principal }")
	@Override
	void delete(Todo entity);

	@Modifying
	@Query("delete from Todo as m where m.owner = ?#{ principal }")
	@Override
	void deleteAll();

	@Override
	@Query("select CASE WHEN COUNT(m) > 0 THEN 'true' ELSE 'false' END from Todo as m where m.owner = ?#{ principal } and m.id=?1")
	boolean exists(Long id);

}

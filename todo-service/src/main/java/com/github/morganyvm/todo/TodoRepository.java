package com.github.morganyvm.todo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "todos", path = "todos")
public interface TodoRepository extends PagingAndSortingRepository<Todo, Long> {

	List<Todo> findByValue(@Param("value") String name);
}
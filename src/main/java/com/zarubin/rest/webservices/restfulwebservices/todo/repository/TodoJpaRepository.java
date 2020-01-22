package com.zarubin.rest.webservices.restfulwebservices.todo.repository;

import java.util.List;
import com.zarubin.rest.webservices.restfulwebservices.todo.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoJpaRepository extends JpaRepository<Todo, Long>{
    List<Todo> findByUsername(String username);
}

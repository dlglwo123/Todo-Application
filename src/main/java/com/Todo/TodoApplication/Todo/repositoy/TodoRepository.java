package com.Todo.TodoApplication.Todo.repositoy;

import com.Todo.TodoApplication.Todo.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    Optional<Todo> findByTitle(String title);

    Optional<Todo> findByTodoOrder(String todoOrder);
}

package com.TodoApp_1.todo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoDto, Integer>{

}

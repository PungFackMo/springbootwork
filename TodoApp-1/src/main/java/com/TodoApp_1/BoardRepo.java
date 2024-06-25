package com.TodoApp_1;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<BoardDto, Integer>{

}

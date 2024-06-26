package com.TodoApp_1.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<BoardDto, Integer>{

}

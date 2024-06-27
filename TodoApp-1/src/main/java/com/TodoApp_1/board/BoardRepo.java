package com.TodoApp_1.board;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepo extends JpaRepository<BoardDto, Integer>{
	Page<BoardDto> findByTitleContaining(String keyword, Pageable pageable);

	Optional<BoardDto> findById(Integer id);

}

package com.TodoApp_1;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.TodoApp_1.todo.TodoDto;
import com.TodoApp_1.todo.TodoRepo;

@SpringBootTest
class TodoRepoTest {

	@Autowired
	TodoRepo tRepo;
	
	@Test
	void test() {
		TodoDto tDto=new TodoDto();
		tDto.setContent("테스트1");
		tDto.setStartDate(LocalDate.now());
		tDto.setEndDate(null);
		tDto.setCompleted(false);
		tRepo.save(tDto);
		
		TodoDto tDto2=new TodoDto();
		tDto2.setContent("테스트2");
		tDto2.setStartDate(LocalDate.now());
		tDto2.setEndDate(null);
		tDto2.setCompleted(false);
		tRepo.save(tDto2);
	}

}

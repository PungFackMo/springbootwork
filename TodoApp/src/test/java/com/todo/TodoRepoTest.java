package com.todo;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TodoRepoTest {
	@Autowired
	TodoRepo tRepo;
	
	@Test
	void test() {
		TodoEntity tEntity=new TodoEntity();
		tEntity.setContent("스프링 부트 공부");
		tEntity.setCompleted(false);
		tRepo.save(tEntity);
		
		TodoEntity tEntity1=new TodoEntity();
		tEntity1.setContent("Todo 리스트 만들기");
		tEntity1.setCompleted(false);
		tRepo.save(tEntity1);
	}

}

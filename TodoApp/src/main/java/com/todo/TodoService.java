package com.todo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TodoService {
	
	@Autowired
	TodoRepo tRepo;

	//디비에 있는 전체 데이터를 가져 오기
	public List<TodoEntity> getTodos(){
		return tRepo.findAll(); // 전체 데이터를 가져와 준다. - 데이터베이스에서 컨트롤러에서 받아서 페이지로 주기만 하면된다.
	}
	
	//사용자에게 입력 받은 데이터를 디비에 저장
	public void putTodo(TodoEntity tEntity) {
		tRepo.save(tEntity);
	}
	
	//받은 id 값으로 삭제 하기
	public void deleteTodo(Integer id) {
		tRepo.deleteById(id);
	}
	
	
	
}

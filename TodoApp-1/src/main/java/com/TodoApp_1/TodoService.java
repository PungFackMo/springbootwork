package com.TodoApp_1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoService {
	
	@Autowired
	TodoRepo tRepo;
	
	//디비에 있는 전체 데이터 가져오기
	public List<TodoDto> getTodos(){
		return tRepo.findAll();
	}
	
	//입력 받은 데이터 DB에 저장
	public void putTodo(TodoDto tDto) {
		tRepo.save(tDto);
	}
	
	//id 값을 이용하여 한개의 todo 가져오기
	public TodoDto getTodo(Integer id) {
		return tRepo.findById(id).get();
	}
	
	//종료 시간 설정
	public void endTodo(TodoDto tDto) {
		tRepo.save(tDto);
	}
	
	//id 값을 이용하여 todo 삭제
	public void deleteTodo(Integer id) {
		tRepo.deleteById(id);
	}
	
}

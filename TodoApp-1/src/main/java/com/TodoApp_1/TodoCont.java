package com.TodoApp_1;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TodoCont {
	
	@Autowired
	TodoService tService;
	
	@GetMapping("/")
	public String getTodosM(Model model) {
//		for(TodoDto tDto:tService.getTodos()) {
//			System.out.println(tDto);
//		}
		model.addAttribute("todos",tService.getTodos());
		return "mainPage";
	}
	@GetMapping("/todos")
	public String getTodosT(Model model) {
		model.addAttribute("todos",tService.getTodos());
		return "todos";
	}
	@PostMapping("/put")
	public String putTodo(TodoDto tDto) {
//		System.out.println(tDto);
		if(tDto.getCompleted()==null) {
			tDto.setCompleted(false);
		}
		if(tDto.getStartDate()==null) {
			tDto.setStartDate(LocalDate.now());
		}
		tService.putTodo(tDto);
		return "redirect:/todos";
	}

}

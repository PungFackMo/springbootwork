package com.TodoApp_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.TodoApp_1.board.BoardService;
import com.TodoApp_1.todo.TodoService;

@Controller
public class MainCont {
	
	@Autowired
	BoardService bService;
	@Autowired
	TodoService tService;
	
	@GetMapping("/")
	public String getData(Model model) {
		model.addAttribute("todos", tService.getTodos());
		model.addAttribute("boards", bService.getBoards());
		return "/mainPage";
	}

}

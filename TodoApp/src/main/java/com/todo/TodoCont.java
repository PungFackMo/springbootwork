package com.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller // 컨트롤러는 주소를 만들어야함
public class TodoCont {
	
	@Autowired
	TodoService tService;
	
//	get방식 요청: 주소창에 데이터가 노출
//	post방식 요청 : 주소창에 데이터가 노출 안됨
	
//	@GetMapping("/")
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String getTodos(Model model) {
//		for(TodoEntity tEntity : tService.getTodos()) {
//			System.out.println(tEntity);
//		}
//		List<TodoEntity> todos=tService.getTodos();
//		model.addAttribute("todos", todos);
		model.addAttribute("todos", tService.getTodos());
		return "todos";
	}
	
	@PostMapping("/put")
//	public String putTodo(@RequestParam TodoEntity tEntity) {
	public String putTodo(TodoEntity tEntity) {
//		System.out.println(tEntity);
		tEntity.setCompleted(false);
		tService.putTodo(tEntity);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Integer id) {
		System.out.println(id);
		System.out.println("id-*----*-**-*-*-*-*-*-*-*-*-*-*-*");
		tService.deleteTodo(id);
		return "redirect:/";
	}
}






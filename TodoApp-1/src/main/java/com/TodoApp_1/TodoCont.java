package com.TodoApp_1;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.validation.Valid;


@Controller
public class TodoCont {
	
	@Autowired
	TodoService tService;
	
	//메인페이지 할 일 가져오기
	@GetMapping("/")
	public String getTodosM(Model model) {
//		for(TodoDto tDto:tService.getTodos()) {
//			System.out.println(tDto);
//		}
		model.addAttribute("todos",tService.getTodos());
		return "mainPage";
	}
	
	//할일페이지 할 일 가져오기
	@GetMapping("/todos")
	public String getTodosT(Model model) {
		model.addAttribute("todos",tService.getTodos());
		return "todos";
	}
	
	//할 일 추가하기
	@PostMapping("/put")
	public String putTodo(@Valid @ModelAttribute("todoDto") TodoDto tDto, BindingResult bindingResult, Model model) {
//		System.out.println(tDto);
		if (bindingResult.hasErrors()) {
			 MessageDto message = new MessageDto("Please enter new todo!", "/todos", RequestMethod.GET, null);
			 System.out.println(message);
			 return showMessageAndRedirect(message, model); // 유효성 검사 오류 시 오류 페이지로 리디렉션
        }
//		System.out.println("2번째 에러");
		if(tDto.getCompleted()==null) {
			tDto.setCompleted(false);
		}
		if(tDto.getStartDate()==null) {
			tDto.setStartDate(LocalDate.now());
		}
		tService.putTodo(tDto);
		
		return "redirect:/todos";
	}
	
	//할 일 업데이트
	@GetMapping("/update/{id}")
	public String updateTodo(@PathVariable Integer id, Model model) {
		TodoDto tDto=tService.getTodo(id);
		model.addAttribute("todo",tDto);
		return "update-todo";
	}
	
	//종료 시간 체크 - mainPage
	@GetMapping("/endTodoMP")
	public String endTodoMP(TodoDto tDto) {
//        System.out.println(tDto);
		if(tDto.getEndDate()==null) {
			System.out.println(tDto);
			tDto.setStartDate(tDto.getStartDate());
			tDto.setEndDate(LocalDate.now());
			tDto.setCompleted(!tDto.getCompleted());
		} else {
			System.out.println(tDto);
			tDto.setStartDate(tDto.getStartDate());
			tDto.setEndDate(null);
			tDto.setCompleted(!tDto.getCompleted());
		}
		tService.putTodo(tDto);
		return "redirect:/";
	}
	
	//종료 시간 체크 - todos
	@GetMapping("/endTodoT")
    public String endTodoT(TodoDto tDto) {
//        System.out.println(tDto);
        if(tDto.getEndDate()==null) {
        	System.out.println(tDto);
        	tDto.setStartDate(tDto.getStartDate());
            tDto.setEndDate(LocalDate.now());
            tDto.setCompleted(!tDto.getCompleted());
        } else {
        	System.out.println(tDto);
	        tDto.setStartDate(tDto.getStartDate());
            tDto.setEndDate(null);
            tDto.setCompleted(!tDto.getCompleted());
        }
        tService.putTodo(tDto);
        return "redirect:/todos";
    }
	
	//id 값을 가져와서 data 삭제하기
	@GetMapping("/delete/{id}")
	public String deleteTodo(@PathVariable Integer id) {
//		System.out.println(id);
		tService.deleteTodo(id);
		return "redirect:/todos";
	}
	
	
	// 사용자에게 메세지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
		model.addAttribute("params", params);
		return "common/messageRedirect";
	}
}

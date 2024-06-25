package com.TodoApp_1;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardCont {

	@Autowired
	BoardService bService;
	
	//게시글 가져오기
	@GetMapping("/board")
	public String getBoard(Model model) {
		model.addAttribute("boards", bService.getBoards());
		return "board";
	}
	//게시글 가져오기
	@GetMapping("/board-detail/{id}")
	public String getBoardDet(@PathVariable Integer id, Model model) {
		BoardDto bDto = bService.getBoard(id);
		model.addAttribute("board", bDto);
		return "board-detail";
	}
	
	//게시글 만들기
	@GetMapping("/board-update")
	public String newBoard() {
		return "board-update";
	}
	
	//게시글 추가하기
	@PostMapping("/put/board")
	public String putBoard(BoardDto bDto) {
		bDto.setCreatedDateTime(LocalDateTime.now());
		bDto.setUpdatedDateTime(LocalDateTime.now());
		System.out.println(bDto);
		bService.putBoard(bDto);
		return "redirect:/board";
	}
	
//	//게시글 수정하기
//	@GetMapping("/update-board/{id}")
//	public String updateBoard(@PathVariable Integer id, Model model) {
//		BoardDto bDto=new BoardDto();
//		model.addAttribute("board", bDto);
//		return "board-update";
//	}
}

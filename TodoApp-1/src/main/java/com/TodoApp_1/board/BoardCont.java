package com.TodoApp_1.board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.TodoApp_1.comment.CommentDto;
import com.TodoApp_1.comment.CommentService;

@Controller
public class BoardCont {

	@Autowired
	BoardService bService;
	
	@Autowired
	CommentService cService;
	
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
		List<CommentDto> comments=cService.getCommentsByBoardId(id);
		int commentCnt=comments.size();
		model.addAttribute("board", bDto);
		model.addAttribute("comments", comments);
		model.addAttribute("commentCnt", commentCnt);
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
		bService.putBoard(bDto);
		return "redirect:/board";
	}
	
	//댓글 추가하기
	@PostMapping("/put/comment")
	public String putComment(@RequestParam("board") Integer boardId, @RequestParam("comment") String comment) {
		BoardDto board=bService.getBoard(boardId);
		CommentDto cDto=new CommentDto();
		cDto.setBoard(board);
		cDto.setComment(comment);
		cDto.setCreatedCommentDateTime(LocalDateTime.now());
		cService.putComment(cDto);
		return "redirect:/board-detail/" + boardId;
	}
	
//	//게시글 수정하기
//	@GetMapping("/update-board/{id}")
//	public String updateBoard(@PathVariable Integer id, Model model) {
//		BoardDto bDto=new BoardDto();
//		model.addAttribute("board", bDto);
//		return "board-update";
//	}
}

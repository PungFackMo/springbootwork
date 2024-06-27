package com.TodoApp_1.board;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TodoApp_1.MessageDto;
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
    public String getBoard(Model model, 
    								@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable,
    								@RequestParam(required = false, defaultValue = "10") int size) {
        Page<BoardDto> searchList = bService.getBoards(pageable);
        model.addAttribute("boards", searchList.getContent());
        model.addAttribute("previous", pageable.getPageNumber() - 1);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());
        model.addAttribute("size", size);
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
	
	//게시글 수정하기 - 로그인 페이지 이동
	@GetMapping("/login/{id}")
	public String loginBoard(@PathVariable Integer id, Model model) {
		BoardDto bDto=bService.getBoard(id);
		model.addAttribute("board", bDto);
		return "login";
	}
	
	@PostMapping("/authenticate")
	public String authenticateUser(@RequestParam String userName, 
												@RequestParam String userPW, 
												RedirectAttributes redirectAttributes,
												@RequestParam Integer id) {
	    // 여기서 userName과 userPW를 사용하여 사용자 인증을 수행하고,
	    // 성공하면 리디렉션할 경로를 설정합니다.
	    boolean isAuthenticated = bService.authenticate(id,userName, userPW);
	    
	    if (isAuthenticated) {
	        // 인증이 성공하면 board-update 페이지로 리디렉션합니다.
	        return "redirect:/board-update/" + id; // id는 사용자가 선택한 게시글의 ID입니다.
	    } else {
	        // 인증 실패 시 처리
	        redirectAttributes.addFlashAttribute("error", "Authentication failed. Please try again.");
	    	private String showMessageAndRedirect(final MessageDto params, Model model) {
	    		model.addAttribute("params", params);
	    		return "common/messageRedirect";
	    	}
	        return "/";
	    }
	}
	
    @GetMapping("/board-update/{id}")
    public String updateBoard(@PathVariable Integer id, Model model) {
        // 게시글 수정 페이지 로드
        BoardDto bDto = bService.getBoard(id);
        model.addAttribute("board", bDto);
        return "board-update";
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
	
	//검색 기능
	@GetMapping("/board/search")
	public String search(String keyword, Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
		Page<BoardDto> searchList = bService.search(keyword, pageable);
		model.addAttribute("searchList", searchList);
		model.addAttribute("previous", pageable.getPageNumber() - 1);
		model.addAttribute("next", pageable.getPageNumber() + 1);
		model.addAttribute("hasNext", searchList.hasNext());
		model.addAttribute("hasPrev", searchList.hasPrevious());
		model.addAttribute("keyword", keyword);
		return "board-search";
	}
}

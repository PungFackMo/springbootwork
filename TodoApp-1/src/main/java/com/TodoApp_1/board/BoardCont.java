package com.TodoApp_1.board;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TodoApp_1.MessageDto;
import com.TodoApp_1.comment.CommentDto;
import com.TodoApp_1.comment.CommentService;

import jakarta.validation.Valid;

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
    						@RequestParam(required = false, defaultValue = "10") int size,
    						String keyword) {
        Page<BoardDto> searchList = bService.getBoards(pageable);
        model.addAttribute("boards", searchList.getContent());
        model.addAttribute("previous", pageable.getPageNumber() - 1);
        model.addAttribute("next", pageable.getPageNumber() + 1);
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("hasPrev", searchList.hasPrevious());
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
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
	public String newBoard(Model model, String keyword) {
		model.addAttribute("board", new BoardDto());
		model.addAttribute("keyword", keyword);
		return "board-update";
	}
	
	//게시글 추가하기
	@PostMapping("/put/board")
	public String putBoard(@Valid @ModelAttribute("boardDto") BoardDto bDto,
	                       BindingResult bindingResult,
	                       RedirectAttributes redirectAttributes,
	                       @RequestParam String userName,
	                       @RequestParam String userPW,
	                       @RequestParam String title,
	                       @RequestParam String content,
	                       @RequestParam String id,
	                       @RequestParam String createdDateTime,
	                       Model model) {
	    Map<String, String> data = new HashMap<>();
	    data.put("userName", userName);
	    data.put("userPW", userPW);
	    data.put("title", title);
	    data.put("content", content);
	    data.put("id", id);
	    data.put("createdDateTime", createdDateTime);

	    if (bindingResult.hasFieldErrors("userName")) {
	        MessageDto message = new MessageDto("Please enter the Name!", "/board-update", RequestMethod.GET, data);
	        System.out.println(message);
	        return showMessageAndRedirect(message, model); // 유효성 검사 실패
	    }
	    if (bindingResult.hasFieldErrors("userPW")) {
	        MessageDto message = new MessageDto("Please enter the PW!", "/board-update", RequestMethod.GET, data);
	        System.out.println(message);
	        return showMessageAndRedirect(message, model); // 유효성 검사 실패
	    }
	    if (bindingResult.hasFieldErrors("title")) {
	        MessageDto message = new MessageDto("Please enter the title!", "/board-update", RequestMethod.GET, data);
	        System.out.println(message);
	        return showMessageAndRedirect(message, model); // 유효성 검사 실패
	    }
	    if (bindingResult.hasFieldErrors("content")) {
	        MessageDto message = new MessageDto("Please enter the content!", "/board-update", RequestMethod.GET, data);
	        System.out.println(message);
	        return showMessageAndRedirect(message, model); // 유효성 검사 실패
	    }
	    if (createdDateTime!=null) {
	    	bDto.setCreatedDateTime(LocalDateTime.now());
	    }
	    bDto.setUpdatedDateTime(LocalDateTime.now());
	    // 유효성 검사 통과한 경우
	    bService.putBoard(bDto);

	    return "redirect:/board";
	}
	
	//status 설정
    @ModelAttribute("status")
    public String getStatus() {
        return "";
    }
	
	//게시글 삭제하기 - 로그인 페이지 이동
	@GetMapping("login/delete/{id}")
	public String deleteBoard(@PathVariable Integer id, Model model) {
		BoardDto bDto=bService.getBoard(id);
		model.addAttribute("board", bDto);
		model.addAttribute("status", "delete");
		return "login";
	}
	
	//게시글 삭제하기
	@GetMapping("/delete/board/{id}")
	public String deleteBoard(@RequestParam String userName, 
							@RequestParam String userPW,
							@RequestParam Integer id,
							@Valid @ModelAttribute("boardDto") BoardDto bDto, 
							BindingResult bindingResult,
							RedirectAttributes redirectAttributes,
							Model model) {
	    boolean isAuthenticated = bService.authenticate(id,userName, userPW);
	    
	    if (isAuthenticated) {
	        // 인증이 성공하면 board-update 페이지로 리디렉션합니다.
	    } else if(bindingResult.hasErrors()){
	        // 공백 시 처리
	    	MessageDto message = new MessageDto("Please enter the correct password!", "/login/delete/" + id, RequestMethod.GET, null);
			 System.out.println(message);
			 return showMessageAndRedirect(message, model); // 유효성
	    } else {
	        // 인증 실패 시 처리
	    	MessageDto message = new MessageDto("Please enter the correct password!", "/login/delete/" + id, RequestMethod.GET, null);
			 System.out.println(message);
			 return showMessageAndRedirect(message, model); // 유효성
	    }
		bService.deleteBoard(id);
		return "redirect:/board";
	}
	
	//게시글 수정하기 - 로그인 페이지 이동
	@GetMapping("/login/update/{id}")
	public String loginBoard(@PathVariable Integer id, Model model) {
	    Map<String, String> data = new HashMap<>();
		BoardDto bDto=bService.getBoard(id);
		data.put("userName", bDto.getUserName());
		data.put("userPW", bDto.getUserPW());
		data.put("title", bDto.getTitle());
		data.put("content", bDto.getContent());
		model.addAttribute("board", bDto);
		model.addAttribute("status", "update");
		return "login";
	}
	
	//게시글 수정하기 - 로그인 유효성 체크
	@PostMapping("/authenticate")
	public String authenticateUser(@RequestParam String userName, 
									@RequestParam String userPW,
									@RequestParam Integer id,
									@Valid @ModelAttribute("boardDto") BoardDto bDto, 
									BindingResult bindingResult,
									RedirectAttributes redirectAttributes,
									Model model) {
	    // 여기서 userName과 userPW를 사용하여 사용자 인증을 수행하고,
	    // 성공하면 리디렉션할 경로를 설정합니다.
	    boolean isAuthenticated = bService.authenticate(id,userName, userPW);
	    if (isAuthenticated) {
	        // 인증이 성공하면 board-update 페이지로 리디렉션합니다.
	    } else if(bindingResult.hasErrors()){
	        // 공백 시 처리
	    	MessageDto message = new MessageDto("Please enter the password!", "/login/update/" + id, RequestMethod.GET, null);
			 System.out.println(message);
			 return showMessageAndRedirect(message, model); // 유효성
	    } else {
	        // 인증 실패 시 처리
	    	MessageDto message = new MessageDto("Please enter the correct password!", "/login/update/" + id, RequestMethod.GET, null);
			 System.out.println(message);
			 return showMessageAndRedirect(message, model); // 유효성
	    }
        return "redirect:/board-update/" + id; // id는 사용자가 선택한 게시글의 ID입니다.

	}
	
	// 사용자에게 메세지를 전달하고, 페이지를 리다이렉트 한다.
	private String showMessageAndRedirect(final MessageDto params, Model model) {
	    model.addAttribute("params", params);
	    return "common/messageRedirect";
	}
	
	//게시글 수정하기
    @GetMapping("/board-update/{id}")
    public String updateBoard(@PathVariable Integer id, Model model) {
        // 게시글 수정 페이지 로드
        BoardDto bDto = bService.getBoard(id);
        model.addAttribute("board", bDto);
        return "board-update";
    }
	
	
	//comment 추가하기
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
	
	//id 값을 가져와서 comment 삭제하기
	@GetMapping("/delete/comment/{id}")
	public String deleteComment(@PathVariable Integer id, @RequestParam("board") Integer boardId) {
	    cService.deleteComment(id);
	    return "redirect:/board-detail/" + boardId;
	}
	
	//검색 기능
	@GetMapping("/board/search")
	public String search(String keyword,
						Model model, 
						@RequestParam(required = false, defaultValue = "10") int size, 
						@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 10) Pageable pageable) {
		Page<BoardDto> searchList = bService.search(keyword, pageable);
		model.addAttribute("searchList", searchList);
		model.addAttribute("previous", pageable.getPageNumber() - 1);
		model.addAttribute("next", pageable.getPageNumber() + 1);
		model.addAttribute("hasNext", searchList.hasNext());
		model.addAttribute("hasPrev", searchList.hasPrevious());
		model.addAttribute("keyword", keyword);
		model.addAttribute("size", size);
		return "board-search";
	}
}

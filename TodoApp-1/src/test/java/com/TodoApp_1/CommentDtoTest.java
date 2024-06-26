package com.TodoApp_1;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.TodoApp_1.board.BoardDto;
import com.TodoApp_1.board.BoardRepo;
import com.TodoApp_1.comment.CommentDto;
import com.TodoApp_1.comment.CommentRepo;

@SpringBootTest
class CommentDtoTest {
	
	@Autowired
	CommentRepo cRepo;
	
	@Autowired
	BoardRepo bRepo;

	@Test
	void test() {
		BoardDto bDto=new BoardDto();
		bDto.setTitle("e 게시글");
		bDto.setContent("이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.이것은 테스트 글입니다.");
		bDto.setUserName("김e바");
		bDto.setUserPW("121212");
		bDto.setCreatedDateTime(LocalDateTime.now());
		bDto.setUpdatedDateTime(LocalDateTime.now());
		bRepo.save(bDto);
		
		CommentDto cDto = new CommentDto();
		cDto.setBoard(bDto);
		cDto.setComment("test");
		cDto.setCreatedCommentDateTime(LocalDateTime.now());
		cRepo.save(cDto);
	}

}

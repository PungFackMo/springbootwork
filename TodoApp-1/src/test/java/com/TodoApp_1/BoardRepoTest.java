package com.TodoApp_1;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.TodoApp_1.board.BoardDto;
import com.TodoApp_1.board.BoardRepo;

@SpringBootTest
class BoardRepoTest {
	
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
		
		BoardDto bDto2=new BoardDto();
		bDto2.setTitle("첫 게시글");
		bDto2.setContent("첫 게시글 내용");
		bDto2.setUserName("김장바");
		bDto2.setUserPW("121212");
		bDto2.setCreatedDateTime(LocalDateTime.now());
		bDto2.setUpdatedDateTime(LocalDateTime.now());
		bRepo.save(bDto2);
	}

}

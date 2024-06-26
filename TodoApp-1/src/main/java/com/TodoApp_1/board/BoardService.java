package com.TodoApp_1.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

	@Autowired
	BoardRepo bRepo;
	
	//디비에 있는 전체 데이터 가져오기
	public List<BoardDto> getBoards(){	
		return bRepo.findAll();
	}
	
	//디비에 저장
	public void putBoard(BoardDto bDto) {
		bRepo.save(bDto);
	}
	
	//id를 이용하여 board 가져오기
	public BoardDto getBoard(Integer id) {
		return bRepo.findById(id).get();
	}
}

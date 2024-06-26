package com.TodoApp_1.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
	
	@Autowired
	CommentRepo cRepo;
	
	public List<CommentDto> getComments() {
		return cRepo.findAll();
	}
	
	//디비에 저장
	public void putComment(CommentDto cDto) {
		cRepo.save(cDto);
	}

    public List<CommentDto> getCommentsByBoardId(Integer boardId) {
        return cRepo.findByBoardId(boardId);
    }
}

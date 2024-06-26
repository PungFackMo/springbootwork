package com.TodoApp_1.comment;

import java.time.LocalDateTime;

import com.TodoApp_1.board.BoardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class CommentDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; 
	private String comment;
	private LocalDateTime createdCommentDateTime;
	@ManyToOne
	@JoinColumn(name="boardId")
	private BoardDto board;

}

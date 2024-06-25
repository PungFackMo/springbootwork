package com.TodoApp_1;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class BoardDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 100, nullable = false)
	private String title;
	@Column(length = 1000, nullable = false)
	private String content;
	@Column(nullable = false)
	private String userName;
	@Column(nullable = false)
	private String userPW;
	@Column(nullable = false)
	private LocalDateTime createdDateTime;
	@Column(nullable = false)
	private LocalDateTime updatedDateTime;
	@Column(nullable = true)
	private String comment;
	private LocalDateTime createdCommentDateTime;
	
	
}

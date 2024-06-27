package com.TodoApp_1.board;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
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
	@NotBlank
	private String title; 
	@Column(length = 1000, nullable = false)
	@NotBlank
	private String content;
	@Column(nullable = false)
	@NotBlank
	private String userName;
	@Column(nullable = false)
	@NotBlank
	private String userPW;
	@Column(nullable = false)
	private LocalDateTime createdDateTime;
	@Column(nullable = false)
	private LocalDateTime updatedDateTime;
	
	
}

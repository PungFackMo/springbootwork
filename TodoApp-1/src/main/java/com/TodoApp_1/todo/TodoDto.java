package com.TodoApp_1.todo;


import java.time.LocalDate;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

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
public class TodoDto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(length = 200, nullable = false)
	@NotBlank(message = "Todo content must not be blank")
	private String content;
	@Column(nullable = false)
	private LocalDate startDate;
	private LocalDate endDate;
	@Column(nullable = false)
	private Boolean completed;

}

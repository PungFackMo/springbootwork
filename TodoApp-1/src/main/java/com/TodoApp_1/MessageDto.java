package com.TodoApp_1;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MessageDto {

	private String message;				// 사용자에게 전달할 메세지
	private String url;			// 리다이렉트 URL
	private RequestMethod method;		// HTTP 요청 메소드
	private Map<String, String> data;	// 화면(View)으로 전달할 데이터(파라미터)
}

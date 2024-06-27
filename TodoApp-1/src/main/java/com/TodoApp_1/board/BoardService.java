package com.TodoApp_1.board;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class BoardService {

	@Autowired
	BoardRepo bRepo;
	
	//디비에 있는 전체 데이터 중 내림차순으로 5개만 가져오기
	public List<BoardDto> getBoardsOnlyTen(){	
		PageRequest pageRequest = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "id"));
		return bRepo.findAll(pageRequest).getContent();
	}
	
	//디비에 있는 전체 데이터 가져오기
    public Page<BoardDto> getBoards(Pageable pageable) {
        return bRepo.findAll(pageable);
    }
	
	//디비에 저장
	public void putBoard(BoardDto bDto) {
		bRepo.save(bDto);
	}
	
	//id를 이용하여 board 가져오기
	public BoardDto getBoard(Integer id) {
		return bRepo.findById(id).get();
	}
	
    public boolean authenticate(Integer id, String userName, String userPW) {
    	Optional<BoardDto> user = bRepo.findById(id);
        // 여기서 실제 사용자 인증 로직을 구현합니다.
        // 예를 들어, 데이터베이스에서 사용자 이름과 비밀번호를 확인하여 인증을 수행할 수 있습니다.
        // 이 예제에서는 단순히 "admin"이라는 사용자명과 "password"라는 비밀번호로 하드코딩된 예시를 보여줍니다.
        
        if (user.get().getUserName().equals(userName)&&user.get().getUserPW().equals(userPW)) {
            return true;
        } else {
            return false;
        }
    }
	
	//검색기능
	@Transactional
	public Page<BoardDto> search(String keyword, Pageable pageable){
		return bRepo.findByTitleContaining(keyword, pageable);
	}
}

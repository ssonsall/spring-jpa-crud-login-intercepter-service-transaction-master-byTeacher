package com.cos.crud.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.crud.model.Board;
import com.cos.crud.model.User;
import com.cos.crud.repository.BoardRepository;

@Service
public class BoardService {

	
	private static final Logger log = LoggerFactory.getLogger(BoardService.class);

	
	@Autowired
	private BoardRepository mRepo;
	
	// 트랜잭션 테스트
	@Transactional
	public void increaseCountAndTimeUpdate(int id) {
		try {
			mRepo.increaseCount(id); //정상
			mRepo.timeUpdate(id); //실패
		} catch (Exception e) {
			
			throw new RuntimeException(e);
		}

	}
	
	// DB관련로직만 서비스에 필요하다.
	public List<Board> boardList() {	
		List<Board> boards = mRepo.findAll();
		return boards;
	}
	
	public Board boardDetail(int id) {
		Optional<Board> board = mRepo.findById(id);
		return board.get();
	}
	
	public int boardWrite(Board board, HttpSession session) {
		try {
			User user = (User)session.getAttribute("user");
			board.setUser(user);
			mRepo.save(board);
			
			return 1;
		} catch (Exception e) {
			System.out.println("boardWrite 오류");
		}
		return -1;
	}
	
	public int boardDelete(int id) {
		try {
			mRepo.deleteById(id);
			return 1;
		} catch (Exception e) {
			System.out.println("boardDelete 오류");
		}
		return -1;
	}

	public Board boardUpdateForm(int id) {
		Optional<Board> board = mRepo.findById(id);
		return board.get();
	}
	
	public int boardUpdate(Board board) {
		try {
			mRepo.save(board);
			return 1;
		} catch (Exception e) {
			System.out.println("boardUpdate 오류");
		}	
		return -1;
	}
}









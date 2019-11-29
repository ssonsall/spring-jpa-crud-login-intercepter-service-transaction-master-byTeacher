package com.cos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.cos.crud.model.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	// update => Transactional + Modifying
	// insert => Transactional
	// delete => Transactional
	
	// 트랜잭션 테스트 1
	@Transactional
	@Modifying
	@Query(value="UPDATE board SET count=count+1 WHERE id=?1", nativeQuery = true)
	public void increaseCount(int id);
	
	// 트랜잭션 테스트 2
	@Transactional
	@Modifying
	@Query(value="UPDATE board SET createDate=no2w() WHERE id=?1", nativeQuery = true)
	public void timeUpdate(int id);
}

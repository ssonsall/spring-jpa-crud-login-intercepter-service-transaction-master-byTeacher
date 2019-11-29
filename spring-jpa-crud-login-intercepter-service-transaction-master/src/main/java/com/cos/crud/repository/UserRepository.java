package com.cos.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cos.crud.model.User;

// CRUD를 구현해주는 클래스 JpaRepository
public interface UserRepository extends JpaRepository<User, Integer>{
	// Naming 규칙
	User findByEmailAndPassword(String email, String password);
}

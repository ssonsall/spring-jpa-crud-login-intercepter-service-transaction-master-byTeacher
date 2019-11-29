package com.cos.crud.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DynamicInsert
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 30)
	private String username;
	@Column(nullable = false, length = 20)
	private String password;
	private String phone;
	private String email;
	@CreationTimestamp //시간 자동입력
	private Timestamp createDate;
	@ColumnDefault("'user'")
	private String role;
	
	// 양방향 매핑하기
    // 연관관계의 주인이 아님을 설정하면 DB에 FK가 생기지 않는다. 
	// (mappedBy = "변수명")
	// @OneToMany(mappedBy = "user")
	// private List<Board> boards;
	
	// 양방향 매핑시 무한 참조 방지
	// 정방향 @JsonManagedReference
	// 역방향 @JsonBackReference
	
	// 해당 필드 무시 @JsonIgnore
	// 해당 필드(오브젝트)안에 user 필드만 무시 @JsonIgnoreProperties({"user"})
	
	
}

package com.cos.blog.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int id; 
	
	@Column(nullable = false, length = 500)
	private String title;
	
	@Lob // 대용량 데이터
	private String content; 
	
	private int count; // 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)  
	@JoinColumn(name="userId")
	private User user; // DB는 오브젝트를 저장할 수 없음
	
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) 
	@JsonIgnoreProperties({"board"})
	@OrderBy("id desc")
	private List<Reply> replys;
	
	@CreationTimestamp
	private LocalDateTime createDate;
}
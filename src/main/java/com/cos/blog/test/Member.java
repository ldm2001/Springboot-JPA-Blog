package com.cos.blog.test;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   
@NoArgsConstructor  
public class Member {
	private int id;
	private String username;
	private String password;
	private String  eamil;
	
	@Builder
	public Member(int id, String username, String password, String eamil) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.eamil = eamil;
	}
}
package com.cos.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean // IoC
	public BCryptPasswordEncoder encodePWD() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		// CSRF 비활성화
		http.csrf(c -> c.disable());

		// 인증 주소 설정 (WEB-INF/** 추가해줌
		// 나중에 인증이 필요한 주소로 무한 리다이렉션 일어날수도 있다함
		http.authorizeHttpRequests(authorize -> authorize
				.requestMatchers("/WEB-INF/**", "/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**")
				.permitAll().anyRequest().authenticated());

		// 로그인 처리 프로세스 설정
		http.formLogin(
				f -> f.loginPage("/auth/loginForm").loginProcessingUrl("/auth/loginProc").defaultSuccessUrl("/"));
		return http.build();
	}
}
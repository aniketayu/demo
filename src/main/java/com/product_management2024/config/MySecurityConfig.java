package com.product_management2024.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.product_management2024.service.UserDetailServiceImpl;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
public class MySecurityConfig {
	
	@Autowired
	private JwtAuthenticationEntrypoint jwtAuthenticationEntrypoint;
	
	@Autowired
	private JwtAuthenticationFilter jwauAuthenticationFilter;
	
	public UserDetailServiceImpl userDetailServiceImpl() {
		return new UserDetailServiceImpl();
	}
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailServiceImpl());
		authenticationProvider.setPasswordEncoder(PasswordEncoder());
		return authenticationProvider;
	}

	@Bean
	public BCryptPasswordEncoder PasswordEncoder() {
		// TODO Auto-generated method stub
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception{
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
		return http.csrf().disable().authorizeHttpRequests()
				.requestMatchers("/user/generate-token","/user/us").permitAll().and()
				.authorizeHttpRequests().requestMatchers(HttpMethod.OPTIONS).permitAll().anyRequest()
				.authenticated().and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntrypoint)
				.and()
				.authenticationProvider(authenticationProvider())
				.addFilterBefore(jwauAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
				.build();

	
	}
	
	

}

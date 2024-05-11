package com.product_management2024.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.product_management2024.model.User;

@EnableJpaRepositories
public interface UserRepo extends JpaRepository<User, Long> {
	
	public User findByUsername(String username);

}


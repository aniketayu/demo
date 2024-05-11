package com.product_management2024.service;



import java.util.List;

import com.product_management2024.model.User;

public interface UserService {
	
	public User addUser(User user);
	
	public List<User> getAlluser();

}

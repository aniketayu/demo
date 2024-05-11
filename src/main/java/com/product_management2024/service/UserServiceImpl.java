package com.product_management2024.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_management2024.model.User;
import com.product_management2024.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public User addUser(User user) {
		// TODO Auto-generated method 

		User user1=this.userRepo.findByUsername(user.getUsername());
		
		if(user1 != null) {
			System.out.println("user is already present");
			
		}else {
			user1=this.userRepo.save(user);
		}
		return user1;
	}

	@Override
	public List<User> getAlluser() {
		// TODO Auto-generated method stub
		return this.userRepo.findAll();
	}

}

package com.product_management2024.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.product_management2024.model.User;
import com.product_management2024.repository.UserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=this.userRepo.findByUsername(username);
		if(user == null) {
			System.out.println("user not found");
			throw new UsernameNotFoundException("user not found");
		}
		
		return user;
	}

}

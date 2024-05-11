package com.product_management2024.controller;



import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product_management2024.config.JwtUtil;
import com.product_management2024.model.JwtRequest;
import com.product_management2024.model.JwtResponse;
import com.product_management2024.model.User;
import com.product_management2024.service.UserDetailServiceImpl;
import com.product_management2024.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@GetMapping("/us")
	public String home() {
		
		return "home page user";
		
	}
	
	@PostMapping("/us")
	public User adduser(@RequestBody User user)throws Exception {
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRole("1");
		
		return userService.addUser(user);
	}
	
	@PostMapping("/generate-token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest)throws Exception
	{
		try {
			UserDetails userDetails=this.userDetailServiceImpl.loadUserByUsername(jwtRequest.getUsername());
			String token=this.jwtUtil.generateToken(userDetails);
			System.out.println(token);
			return ResponseEntity.ok(new JwtResponse(token));
		} catch (UsernameNotFoundException e) {
			e.printStackTrace();
			System.out.println("catch block executed");
			throw new Exception("user not found");
			// TODO: handle exception
		}
		
	}
	
	@GetMapping("/current-user")
	public User getcurrentUser(Principal principal) {
		return ((User)this.userDetailServiceImpl.loadUserByUsername(principal.getName()));
	}

}

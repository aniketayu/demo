package com.product_management2024.model;



import org.springframework.security.core.GrantedAuthority;

public class Authority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String authority;
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.authority;
	}
	
	public Authority(String authority) {
		this.authority=authority;
	}

	
	

	

}


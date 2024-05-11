package com.product_management2024.config;



import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.product_management2024.service.UserDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String requestTokenHeader=request.getHeader("Authorization");
		String username=null;
		String jwtToken=null;
	
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			
			jwtToken=requestTokenHeader.substring(7);
			
			try {
				
				username=this.jwtUtil.extractUsername(jwtToken);
				
			}catch(ExpiredJwtException e) {
				e.printStackTrace();
				System.out.println("token expired");
			}
			
			catch (Exception e) {
				
				e.printStackTrace();
				System.out.println("error");			}
			
		
		
		//validate token
		
		
		if(username !=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			final UserDetails userDetails=this.userDetailServiceImpl.loadUserByUsername(username);
			
			if(this.jwtUtil.validateToken(jwtToken, userDetails)) {
				//token is valid
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}else {
			System.out.println("token is not valid");
		}
		
		
	}
	
		filterChain.doFilter(request, response);
	}
}

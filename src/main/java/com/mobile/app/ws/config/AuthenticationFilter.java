package com.mobile.app.ws.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mobile.app.ws.dto.UserDto;
import com.mobile.app.ws.model.request.UserLoginRequest;
import com.mobile.app.ws.service.impl.CustomUserDetailsService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	private CustomUserDetailsService userDetailsService;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager,
			CustomUserDetailsService userDetailsService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			UserLoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
			
			return authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), 
																		  loginRequest.getPassword(), 
																		  new ArrayList<>()));
		} 
		catch (IOException e) {
			throw new RuntimeException(e.getLocalizedMessage());
		}
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String username = ((User)authResult.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
						   .setSubject(username)
						   .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
						   .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
						   .compact();
		response.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		
		UserDto userDto = userDetailsService.getUserByEmail(username);
		
		response.addHeader("UserId", userDto.getUserId());
	}
}
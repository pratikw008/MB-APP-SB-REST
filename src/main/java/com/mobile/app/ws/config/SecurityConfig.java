package com.mobile.app.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mobile.app.ws.service.impl.CustomUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	private final CustomUserDetailsService userDetailsService;
	
	public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserDetailsService userDetailsService) {
		super();
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
			.permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilter(getAthenticationFilter(authenticationManagerBean(), userDetailsService))
			.addFilter(new AuthorizationFilter(authenticationManager()))
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	private AuthenticationFilter getAthenticationFilter(AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService) {
		AuthenticationFilter authenticationFilter = new AuthenticationFilter(authenticationManager, userDetailsService);
		authenticationFilter.setFilterProcessesUrl(SecurityConstants.LOGIN_URL);
		return authenticationFilter;
	}
}

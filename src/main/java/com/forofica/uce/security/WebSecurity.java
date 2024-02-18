package com.forofica.uce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurity {
	
	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
		.exceptionHandling()
		.authenticationEntryPoint(unauthorizedHandler)
		.and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS) //Como se va a manejar la sesion, se mantiene mientras se consulte el API
		.and()
		.authorizeRequests()
		.anyRequest()
		.authenticated();
		
		http.addFilterBefore(this.authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
	@Bean
	public AuthTokenFilter authenticationTokenFilter() {
		return new AuthTokenFilter();
	}
}

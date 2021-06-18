package com.in28minutes.springboot.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		
		auth.inMemoryAuthentication().passwordEncoder(org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance())
		.withUser("user1").password("secret1")
		.roles("USER").and().withUser("admin1").password("secret1")
		.roles("USER","ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		
		http.httpBasic().and().
		authorizeRequests()
		.antMatchers("/users/**").hasRole("USER")
		.antMatchers("/**").hasRole("ADMIN")
		.antMatchers("/surveys/**").hasRole("USER").and().csrf().disable()
        .headers().frameOptions().disable();
	}
	
}
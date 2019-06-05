package com.wirecard.challenge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void createGlobalConfiguration(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.inMemoryAuthentication().withUser("wirecardchallenge").password("{noop}testes12").roles("USER");
	}
	
	protected void createConfiguration(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.authorizeRequests()
//			.antMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
			.and().httpBasic()
			.and().csrf().disable();
	}
	
}

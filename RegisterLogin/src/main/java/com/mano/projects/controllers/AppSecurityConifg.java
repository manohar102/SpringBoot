package com.mano.projects.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableWebSecurity
public class AppSecurityConifg extends WebSecurityConfigurerAdapter {
	
	@Autowired
    public UserDetailsService userDetailsService;
//	
//	@Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http 
		.authorizeRequests() 
		.antMatchers("/users","/login","/register","/register_process").permitAll()
		.anyRequest().authenticated()
		.and().csrf().disable()
		.formLogin()
		.loginPage("/login").defaultSuccessUrl("/profile").failureUrl("/login?error=true")
		.and().logout().logoutSuccessUrl("/login").permitAll(); 
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public AuthenticationProvider authProvider() {	
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());	
		return provider;
	}
	
	
}

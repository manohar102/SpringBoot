///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package com.notes;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// *
// * @author Kasyap
// */
//@Configuration
//public class NotesSecurity extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    UserDetailsService userDetailsService;
//    
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/","/signin","/signup","/register","/login").permitAll()
//                .anyRequest().authenticated();
//                
//             
//        
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        
//        auth.authenticationProvider(authProvider()); 
//        
//    }
//    
//
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////         auth.userDetailsService(userDetailsService).();
////    }
//    @Bean
//    public AuthenticationProvider authProvider()
//    {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
//        provider.setUserDetailsService(userDetailsService);
//        
//        return provider;
//    }
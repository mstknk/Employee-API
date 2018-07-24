package com.code.challenge.employee.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SpringSecConfig/** extends WebSecurityConfigurerAdapter */{

//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication() // for inMemory Authentication
//				.withUser("admin").password("{noop}admin").roles("USER") //
//				.and().withUser("admin").password("{noop}admin").roles("USER");
//	}

//	@Override
//	    protected void configure(HttpSecurity http) throws Exception {
//	        http
//	                .httpBasic()                      
//	                .and()
//	                .authorizeRequests()
//	                .antMatchers().permitAll() 
//	                .anyRequest().authenticated();   
//	                 http.csrf().disable();    
//}
}
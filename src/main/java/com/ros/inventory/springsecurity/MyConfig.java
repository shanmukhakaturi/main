package com.ros.inventory.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class MyConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public UserDetailsService getUserDetailsService()
	{
		return new UserDetailsServiceImpl();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(this.getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		// TODO Auto-generated method stub
		http.authorizeRequests().antMatchers("/user/join").permitAll().antMatchers("/user/**").hasRole("USER").antMatchers("/createnewpurchaseorder").hasRole("ADMIN").
		antMatchers("/submitindividualpurchaseorder").hasAnyRole("USER","ADMIN").
		antMatchers("/submitIndividualPurchaseOrder").hasAnyRole("USER","ADMIN").
		antMatchers("/submitBulkjpurchaseorder").hasAnyRole("USER","ADMIN").
		antMatchers("/approveindividualpurchaseorder").hasRole("ADMIN").
		antMatchers("/approveBulkjpurchaseorder").hasRole("ADMIN").
		antMatchers("/approveAlljpurchaseorder").hasRole("ADMIN").
		and().
		formLogin().and().logout().and().
		csrf().disable();
		
	}
	
	

}

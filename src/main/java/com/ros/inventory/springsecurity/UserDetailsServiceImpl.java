package com.ros.inventory.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Account account=userRepository.findByUsername(username);
		if(account ==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		
		CustomUserDetail customUserDetail=new CustomUserDetail(account);
		return customUserDetail;
	}

}

package com.ros.inventory.controller;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.springsecurity.Account;
import com.ros.inventory.springsecurity.AccountDto;
import com.ros.inventory.springsecurity.UserConstant;
import com.ros.inventory.springsecurity.UserRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@PostMapping("/join")
	public String joinGroup(@RequestBody AccountDto user)
	{
		Account account=new Account();
		account.setName(user.getUsername());
//		account.setJobRole(UserConstant.DEFAULT_ROLE);
		account.setJobRole(user.getJobRole());
		account.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(account);
		
		return "saved";
	}
	
//	if loggedin user is ADMIN -> ADMIN OR MODERATOR
//	if loggedin user is MODERATOR ->  MODERATOR
	
	@GetMapping("/access/{userId}/{userRole}")
	public String giveAcessToUser(@PathVariable UUID id,@PathVariable String role,Principal principal)
	{
		Account user=userRepository.findById(id).get();
		List<String> activeRoles=getRolesByLoggedInUser(principal);
		String newRole="";
		if(activeRoles.contains(role))
		{
			newRole=user.getJobRole()+","+role;
		}
		userRepository.save(user);
		return "ACCESS ISSUED";
		
	}
	
	@GetMapping
	public List<Account> loadUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/test")
	public String testUserAccess()
	{
		return "user can only access this";
	}
	private List<String> getRolesByLoggedInUser(Principal principal)
	{
		String roles=getLoggedInUser(principal).getJobRole();
		List<String> assignRoles=Arrays.stream(roles.split(",")).collect(Collectors.toList());
	  if(assignRoles.contains("ROLE_ADMIN"))
	  {
		  return Arrays.stream(UserConstant.ADMIN_ACCESS).collect(Collectors.toList());
	  }
	  if(assignRoles.contains("ROLE_MODERATOR"))
	  {
		  return Arrays.stream(UserConstant.MODERATOR_ACCESS).collect(Collectors.toList());
	  }
	  return Collections.emptyList();
	}
	private Account getLoggedInUser(Principal principal)
	{
		return userRepository.findByUserName(principal.getName()).get();
	}
}

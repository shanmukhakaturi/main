package com.ros.inventory.springsecurity;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

	
   private String username;
 

	private String password;
	
//	private boolean active;
	
	private String jobRole;
	
}

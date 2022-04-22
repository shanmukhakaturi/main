package com.ros.inventory.springsecurity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="accounts")
public class Account implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private String name;
	
	private String password;
	
//	private boolean active;

	private String jobRole;
	
}

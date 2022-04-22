package com.ros.inventory.model.supplier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;
	
	@NotNull
	private String BankName;
	
	@NotNull
	private long accNo;

	private String branch;

	private String address;
	
	@NotNull
	private long mobile;

	private long telephone;
	
	@NotNull
	private String IFSC;

	private long pinCode;

}

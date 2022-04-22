package com.ros.inventory.model.supplier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierContactInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@NotNull
	private String name;
	
	@NotNull
	private String code;

	private String description;
	
	@NotNull
	private long phoneNo;
	
	@NotNull
	private String email;

	private String fax;
	
	@OneToOne
//	@JoinColumn(name = "SuppplierAdress_id", referencedColumnName = "SupplierAddress_id")
	private SupplierAddress addressInfo;

}

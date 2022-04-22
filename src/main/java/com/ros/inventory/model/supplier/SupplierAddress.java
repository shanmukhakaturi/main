package com.ros.inventory.model.supplier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class SupplierAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String country;

	private String code;

	private String state;

	private String city;

	private String addressLine1;

	private String addressLine2;

	@NotNull
	private long pinCode;

//	@JoinColumn(name = "AddressType")
	@Enumerated(EnumType.STRING)
	private AddressType addressType;

}

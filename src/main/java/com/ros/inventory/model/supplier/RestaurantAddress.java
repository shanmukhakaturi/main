package com.ros.inventory.model.supplier;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantAddress {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String Street;

	private String city;

	private String state;

	private String country;

	private long pincode;

}

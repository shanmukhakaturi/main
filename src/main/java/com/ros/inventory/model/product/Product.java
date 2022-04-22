package com.ros.inventory.model.product;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.inventory.model.purchaseorder.UnitOfMeasurement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String tags;
	
	private String productCode;

	private double quantity;

	@Enumerated(EnumType.STRING)
	private UnitOfMeasurement unitOfMeasurement;

	private double pricePerUnit;

	@OneToOne(cascade = {CascadeType.ALL})
	private ProductCategory productCategory;
	
	public Product(UUID id, String name, String tags, String productCode, double quantity,
			UnitOfMeasurement unitOfMeasurement, double pricePerUnit) {
		super();
		this.id = id;
		this.name = name;
		this.tags = tags;
		this.productCode = productCode;
		this.quantity = quantity;
		this.unitOfMeasurement = unitOfMeasurement;
		this.pricePerUnit = pricePerUnit;
	}


	
}

package com.ros.inventory.model.stock;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ros.inventory.model.purchaseorder.ShippingInfoProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String description;

	@OneToOne
//	@JoinColumn(name = "Building_id", referencedColumnName = "Building_id")
	private Building building;

	@OneToOne
//	@JoinColumn(name = "ShippingInfoProduct_id", referencedColumnName = "ShippingInfoProduct_id")
	private ShippingInfoProduct shippingInfoProduct;

}

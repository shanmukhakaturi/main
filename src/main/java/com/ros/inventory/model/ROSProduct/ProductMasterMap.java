package com.ros.inventory.model.ROSProduct;

import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductMasterMap {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	private UUID rosProductCatalogueId;

	private UUID productId;
	
}

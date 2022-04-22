package com.ros.inventory.model.ROSProduct;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class ROSProductCatalogue {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

//	@EmbeddedId
//	private ProductMasterMap productMasterMapid;

	private UUID ROSProductId;

	private long productCategoryId;

	private long ROSProductTypeId;

	private String status;
}

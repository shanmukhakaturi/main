package com.ros.inventory.model.ROSProduct;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.inventory.model.product.ProductCategory;
import com.ros.inventory.model.purchaseorder.UnitOfMeasurement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ROSProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String description;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;

	private double quantity;

	@Enumerated(EnumType.STRING)
	private UnitOfMeasurement unitOfMeasurement;
 
	@OneToOne
//	@JoinColumn(name = "ProductCategory_id", referencedColumnName = "ProductCategory_id")
	private ProductCategory productCategory;
	
	

}

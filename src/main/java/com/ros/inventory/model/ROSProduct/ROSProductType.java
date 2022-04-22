package com.ros.inventory.model.ROSProduct;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ros.inventory.model.product.ProductCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ROSProductType {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name;
	
	private long code;
	
	private String description;
	
	private String createdBy;
	
	private Date createdDate;
	
   private String modifiedBy;
	
	private Date modifiedDate;
	
	@OneToOne
//	@JoinColumn(name = "ProductCategory_id", referencedColumnName = "ProductCategory_id")
	private ProductCategory productCategory;
	
 
	
}

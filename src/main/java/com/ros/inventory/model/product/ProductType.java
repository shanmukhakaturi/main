package com.ros.inventory.model.product;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductType {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String code;

	private String name;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;
	
	@OneToOne
//	@JoinColumn(name = "ProductCategory_id", referencedColumnName = "id")
	private ProductCategory category;

}

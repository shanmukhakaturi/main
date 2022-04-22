
package com.ros.inventory.model.stock;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.ros.inventory.model.invoice.InvoiceProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockCycle {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String description;

	private Date createdDate;
	
	private String createdBy;
	
	private Date startDate;
	
	private String startBy;

	private Date lastModifiedDate;
	
	private String lastModifiedBy;

	private Date closedDate;

	private String closedBy;

	@OneToMany(targetEntity = StockCycle.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "stock_id", referencedColumnName = "id")
	private List<Stock> stocks;

}

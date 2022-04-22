package com.ros.inventory.model.purchaseorder;

import java.util.Date;
import java.util.List;
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

import com.ros.inventory.model.supplier.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;

	private long poNumber;

	private double total;

	private double tax;

	private Date createdDate;

	private String createdBy;

	private Date modifiedDate;

	private String modifiedBy;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getGrandTotal() {
		return (total + tax);
	}

//	@Enumerated(EnumType.STRING)
//	private UnitOfMeasurement unitOfMeasurement;
	
	@Enumerated(EnumType.STRING)
	private PurchaseOrderType PurchaseOrderType;
	
	@Enumerated(EnumType.STRING)
	private PoStatus poStatus;
	
//	@OneToMany(targetEntity = PurchaseOrder.class, cascade = CascadeType.ALL )
//	@JoinColumn(name = "OrderProduct_id", referencedColumnName = "id")
//	private List<OrderProduct> products;

	@OneToOne(cascade = {CascadeType.ALL})
	private Supplier supplier;

public PurchaseOrder(UUID id, String code, long poNumber, double total, double tax, Date createdDate, String createdBy,
		Date modifiedDate, String modifiedBy, com.ros.inventory.model.purchaseorder.PurchaseOrderType purchaseOrderType,
		PoStatus poStatus) {
	super();
	this.id = id;
	this.code = code;
	this.poNumber = poNumber;
	this.total = total;
	this.tax = tax;
	this.createdDate = createdDate;
	this.createdBy = createdBy;
	this.modifiedDate = modifiedDate;
	this.modifiedBy = modifiedBy;
	PurchaseOrderType = purchaseOrderType;
	this.poStatus = poStatus;
}
	
	
}

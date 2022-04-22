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

import com.ros.inventory.model.invoice.InvoiceProduct;
import com.ros.inventory.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;

	private long PoNumber;

	private double total;

	private double tax;

	private Date createdDate;

	private String createdBy;

	private Date modifiedDate;

	private String modifiedBy;
	
	@Enumerated(EnumType.STRING)
	private UnitOfMeasurement unitOfMeasurement;
	
	@Enumerated(EnumType.STRING)
	private PurchaseOrderType PurchaseOrderType;
	
	@OneToMany(targetEntity = PurchaseOrder.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "OrderProduct_id", referencedColumnName = "id")
	private List<OrderProduct> products;
	
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
	
}

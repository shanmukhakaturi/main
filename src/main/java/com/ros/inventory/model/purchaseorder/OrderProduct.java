package com.ros.inventory.model.purchaseorder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.ros.inventory.model.invoice.InvoiceProduct;
import com.ros.inventory.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;

	private String name;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;
	
	@NotNull
	private double total;
	
	@NotNull
	private double tax;

	private double quantity;

	private double price;

	@ManyToOne(cascade = {CascadeType.ALL})
	private PurchaseOrder purchaseOrder;
	
	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "Product_id", referencedColumnName = "Product_id")
	private Product product;

	@OneToMany(targetEntity = OrderProduct.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "InvoiceProduct_id", referencedColumnName = "id")
	private List<InvoiceProduct> invoiceProducts;

	

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}


	public double getGrandTotal() {
		return (total + tax);

	}

	public double getTax1() {
		return tax;
	}

	
}

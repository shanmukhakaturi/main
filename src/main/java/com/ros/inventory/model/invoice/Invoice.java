package com.ros.inventory.model.invoice;

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
import javax.persistence.OneToOne;

import com.ros.inventory.model.supplier.Supplier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String description;

	private double total;

	private double totalTax;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;

	@OneToOne
	private Supplier supplier;

	@OneToMany(targetEntity = Invoice.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoiceproduct_id", referencedColumnName = "id")
	private List<InvoiceProduct> invoiceProduct;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public double getGrandTotal() {
		return (total + totalTax);
	}
}

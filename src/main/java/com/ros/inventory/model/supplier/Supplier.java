package com.ros.inventory.model.supplier;

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
import javax.validation.constraints.NotNull;

import com.ros.inventory.model.invoice.Invoice;
import com.ros.inventory.model.product.Product;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.stock.Rack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@NotNull
	private String supplierName;
	
	private String code;

	private String description;
	
	@NotNull
	private Date createdDate;
	
	@NotNull
	private String createdBy;
	
	@NotNull
	private Date lastModifiedDate;
	
	@NotNull
	private String lastModifedBy;
	
	@Enumerated(EnumType.STRING)
	private SupplierType type;

	@OneToOne
	private BankDetails bankDetails;
	
	@OneToOne
//	@JoinColumn(name = "SupplierContactInfo_id", referencedColumnName = "SupplierContactInfo_id")
	private SupplierContactInfo supplierContactInfo;
	
	@OneToMany(targetEntity = Restaurant.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "restaurant_id", referencedColumnName = "id")
	private List<Restaurant> restaurant;
	
	@OneToMany(targetEntity = Product.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private List<Product> products;
	
	
	@OneToMany(targetEntity = PurchaseOrder.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private List<PurchaseOrder> purchaseOrders;
	
	@OneToMany(targetEntity = Invoice.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private List<Invoice> invoice;
	
	public Supplier(UUID id, @NotNull String supplierName, String code, String description, @NotNull Date createdDate,
			@NotNull String createdBy, @NotNull Date lastModifiedDate, @NotNull String lastModifedBy,
			SupplierType type) {
		super();
		this.id = id;
		this.supplierName = supplierName;
		this.code = code;
		this.description = description;
		this.createdDate = createdDate;
		this.createdBy = createdBy;
		this.lastModifiedDate = lastModifiedDate;
		this.lastModifedBy = lastModifedBy;
		this.type = type;
	}

}

package com.ros.inventory.model.purchaseorder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.inventory.model.invoice.InvoiceProduct;
import com.ros.inventory.model.product.Product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;

	@OneToOne
//	@JoinColumn(name = "PurchaseOrder_id", referencedColumnName = "id")
	private PurchaseOrder purchaseOrder;

	@OneToMany(targetEntity = ShippingInfo.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "ShippingInfoProduct_id", referencedColumnName = "id")
	private List<ShippingInfoProduct> products;

}

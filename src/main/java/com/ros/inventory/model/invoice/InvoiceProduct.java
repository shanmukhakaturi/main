package com.ros.inventory.model.invoice;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.ros.inventory.model.purchaseorder.OrderProduct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class InvoiceProduct {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	@ManyToOne(targetEntity = InvoiceProduct.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_id", referencedColumnName = "id")
	private Invoice invoice;
	
	@OneToOne
	private OrderProduct orderProduct;
}

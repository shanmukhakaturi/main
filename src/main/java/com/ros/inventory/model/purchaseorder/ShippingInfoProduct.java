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
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingInfoProduct {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String code;

	private String name;

	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "OrderProduct_id", referencedColumnName = "OrderProduct_id")
	private OrderProduct orderProduct;

	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "ShippingInfo_id", referencedColumnName = "ShippingInfo_id")
	private ShippingInfo shippingInfo;
}

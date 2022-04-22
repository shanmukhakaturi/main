package com.ros.inventory.model.supplier;

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

import com.ros.inventory.model.stock.Rack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;
	
	@OneToOne
//	@JoinColumn(name = "restaurantAddress_id", referencedColumnName = "restaurantAddress_id")
	private RestaurantAddress restaurantAddress;
	
	@OneToMany(targetEntity = Restaurant.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "supplier_id", referencedColumnName = "id")
	private List<Supplier> supplier;

}

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
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class StockValue {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@OneToMany(targetEntity = StockValue.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "stockCycle_id", referencedColumnName = "id")
	private List<StockCycle> stockCycles;

	@OneToOne
//	@JoinColumn(name = "StockProduct_id", referencedColumnName = "StockProduct_id")
	private StockProduct stockProduct;

	private double quantity;

	private Date effectiveDate;

}

package com.ros.inventory.model.stock;

import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Row {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private long code;
	
	private int number;
	
	@OneToMany(targetEntity = Row.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "cell_id", referencedColumnName = "id")
	private List<Cell> cells;
	
}

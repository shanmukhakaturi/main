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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rack {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String name;

	private String code;

	private String description;

	private Date createdDate;

	private String createdBy;

	private Date lastModifiedDate;

	private String lastModifiedBy;


	@OneToMany(targetEntity = Rack.class, cascade = CascadeType.ALL )
	@JoinColumn(name = "rows_id", referencedColumnName = "id")
	private List<Row> rows;

}

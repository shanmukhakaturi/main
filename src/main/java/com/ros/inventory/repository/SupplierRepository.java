package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.supplier.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID>{

	@Query("select s.id from Supplier s where s.supplierName=supplierName")
	UUID getSupplierId(String supplierName);
	
}

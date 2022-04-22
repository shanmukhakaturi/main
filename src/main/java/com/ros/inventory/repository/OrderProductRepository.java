package com.ros.inventory.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ros.inventory.model.purchaseorder.OrderProduct;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, UUID>{

}

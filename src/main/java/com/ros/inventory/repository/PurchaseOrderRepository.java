package com.ros.inventory.repository;

import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.ros.inventory.model.purchaseorder.OrderProduct;
import com.ros.inventory.model.purchaseorder.PoStatus;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, UUID>{


}

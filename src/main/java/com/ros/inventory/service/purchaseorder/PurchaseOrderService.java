package com.ros.inventory.service.purchaseorder;

import java.util.List;

import com.ros.inventory.controller.dto.purchaseorder.CreateNewPurchaseOrderDto;
import com.ros.inventory.controller.dto.purchaseorder.BulkPurchaseOrderDto;
import com.ros.inventory.exception.ApprovedPurchaseOrderException;
import com.ros.inventory.exception.CreateNewPurchaseOrderException;
import com.ros.inventory.exception.SubmittedPurchaseOrderException;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

public interface PurchaseOrderService {

	CreateNewPurchaseOrderDto createNewPurchaseOrder(CreateNewPurchaseOrderDto createNewPurchaseOrderDto) throws CreateNewPurchaseOrderException;

	String submitIndividualPurchaseOrder(String supplierName) throws SubmittedPurchaseOrderException;

	String submitAllPurchaseOrder()throws SubmittedPurchaseOrderException;

	String approveIndividualPurchaseOrder(String supplierName) throws ApprovedPurchaseOrderException;

	String approveAllPurchaseOrder()throws ApprovedPurchaseOrderException;



}

package com.ros.inventory.mapper.purchaseorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ros.inventory.controller.dto.purchaseorder.CreateNewPurchaseOrderDto;
import com.ros.inventory.model.product.Product;
import com.ros.inventory.model.purchaseorder.OrderProduct;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;

@Mapper
public interface CreateNewPurchaseOrderMapper {

	 @Mapping(target="supplierName",source="purchaseOrder.supplier.supplierName")
	 @Mapping(target="code",source="purchaseOrder.code")
	 @Mapping(target="poNumber",source="purchaseOrder.poNumber")
	 @Mapping(target="createdDate",source="purchaseOrder.createdDate")
	 @Mapping(target="createdBy",source="purchaseOrder.createdBy")
	 @Mapping(target="modifiedDate",source="purchaseOrder.modifiedDate")
	 @Mapping(target="modifiedBy",source="purchaseOrder.modifiedBy")
     CreateNewPurchaseOrderDto convertToDto(PurchaseOrder  purchaseOrder);
	 
	 @Mapping(source="supplierName",target="supplier.supplierName")
	 @Mapping(source="code",target="code")
	 @Mapping(source="poNumber",target="poNumber")
	 @Mapping(source="createdDate",target="createdDate")
	 @Mapping(source="createdBy",target="createdBy")
	 @Mapping(source="modifiedDate",target="modifiedDate")
	 @Mapping(source="modifiedBy",target="modifiedBy")
     PurchaseOrder convertToEntity(CreateNewPurchaseOrderDto  createNewPurchaseOrderDto);
	 
}

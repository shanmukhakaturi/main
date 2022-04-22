package com.ros.inventory.controller.dto.purchaseorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ros.inventory.model.purchaseorder.PurchaseOrderType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder // Automatically produce the code req to have your class be Instantiable with
//code
@AllArgsConstructor
@NoArgsConstructor
public class BulkPurchaseOrderDto {

	private List<String> supplierNameList=new ArrayList();
}

package com.ros.inventory.controller.dto.purchaseorder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import com.ros.inventory.model.product.Product;
import com.ros.inventory.model.product.ProductCategory;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.model.purchaseorder.UnitOfMeasurement;
import com.ros.inventory.model.supplier.Supplier;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

 @Data
 @Builder // Automatically produce the code req to have your class be Instantiable with
//code
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewPurchaseOrderDto {
	
	private String  supplierName;
	
	private String code;

	private long poNumber;

	private Date createdDate;

	private String createdBy;

	private Date modifiedDate;

	private String modifiedBy;
	
	private List<Double> quantites=new ArrayList<>();
	
	private PurchaseOrderType PurchaseOrderType;
	
	private List<String> values=new ArrayList<>();
	
	private List<Double> taxes=new ArrayList<>();
	
	private List<String> orderProductCodes=new ArrayList<>();
	
}

package com.ros.inventory.service.impl.purchaseorder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.inventory.controller.PurchaseOrderController;
import com.ros.inventory.controller.dto.purchaseorder.CreateNewPurchaseOrderDto;
import com.ros.inventory.controller.dto.purchaseorder.BulkPurchaseOrderDto;
import com.ros.inventory.exception.ApprovedPurchaseOrderException;
import com.ros.inventory.exception.CreateNewPurchaseOrderException;
import com.ros.inventory.exception.SubmittedPurchaseOrderException;
import com.ros.inventory.mapper.purchaseorder.CreateNewPurchaseOrderMapper;
import com.ros.inventory.model.product.Product;
import com.ros.inventory.model.purchaseorder.OrderProduct;
import com.ros.inventory.model.purchaseorder.PoStatus;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.supplier.Supplier;
import com.ros.inventory.repository.OrderProductRepository;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.repository.SupplierRepository;
import com.ros.inventory.service.purchaseorder.PurchaseOrderService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

	@Autowired
	CreateNewPurchaseOrderMapper createNewPurchaseOrderMapper;

	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;

	@Autowired
	OrderProductRepository orderProductRepository;

	@Autowired
	SupplierRepository supplierRepository;

	@Override
	public CreateNewPurchaseOrderDto createNewPurchaseOrder(CreateNewPurchaseOrderDto createNewPurchaseOrderDto)
			throws CreateNewPurchaseOrderException {
		// TODO Auto-generated method stub
		double grandTotal = 0;
		double totalTax = 0;

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		UUID id = supplierRepository.getSupplierId(createNewPurchaseOrderDto.getSupplierName());

		List<Supplier> suppliers = supplierRepository.findAll();

		for (Supplier supplier : suppliers) {

			if (supplier.getId().equals(id)) {

				List<Product> Products = supplier.getProducts();

				Iterator<String> values = createNewPurchaseOrderDto.getValues().iterator();
				Iterator<String> orderProductsCodes = createNewPurchaseOrderDto.getOrderProductCodes().iterator();
				Iterator<Double> taxes = createNewPurchaseOrderDto.getTaxes().iterator();
				Iterator<Double> quantities = createNewPurchaseOrderDto.getQuantites().iterator();

				while (values.hasNext() && orderProductsCodes.hasNext() && taxes.hasNext() && quantities.hasNext()) {

					String value = values.next();
					String order = orderProductsCodes.next();
					double tax = taxes.next();
					double quantity = quantities.next();
					totalTax += tax;

					for (Product product : Products) {
						OrderProduct orderProduct = new OrderProduct();

						if (value.equals(product.getName()) || value.equals(product.getProductCode())
								|| value.equals(product.getTags())) {

							orderProduct.setCreatedBy(createNewPurchaseOrderDto.getCreatedBy());
							orderProduct.setCreatedDate(createNewPurchaseOrderDto.getCreatedDate());
							orderProduct.setLastModifiedBy(createNewPurchaseOrderDto.getModifiedBy());
							orderProduct.setLastModifiedDate(createNewPurchaseOrderDto.getModifiedDate());
							orderProduct.setName(product.getName());
							orderProduct.setPrice(product.getPricePerUnit());
							orderProduct.setQuantity(quantity);
							orderProduct.setTax(tax);
							grandTotal += (product.getPricePerUnit() * quantity) + tax;
							orderProduct.setTotal((product.getPricePerUnit() * quantity) + tax);
							orderProduct.setCode(order);
							orderProduct.setProduct(product);
							orderProduct.setPurchaseOrder(purchaseOrder);

						}
						
						orderProductRepository.save(orderProduct);
						log.info("OrderProduct Table is created");
					}
				}

				purchaseOrder.setCreatedBy(createNewPurchaseOrderDto.getCreatedBy());
				purchaseOrder.setCreatedDate(createNewPurchaseOrderDto.getCreatedDate());
				purchaseOrder.setModifiedBy(createNewPurchaseOrderDto.getModifiedBy());
				purchaseOrder.setModifiedDate(createNewPurchaseOrderDto.getModifiedDate());
				purchaseOrder.setPoNumber(createNewPurchaseOrderDto.getPoNumber());
				purchaseOrder.setCode(createNewPurchaseOrderDto.getCode());
				purchaseOrder.setTax(totalTax);
				purchaseOrder.setTotal(grandTotal);
				purchaseOrder.setPurchaseOrderType(createNewPurchaseOrderDto.getPurchaseOrderType());
				purchaseOrder.setSupplier(supplier);
				purchaseOrder.setPoStatus(PoStatus.DRAFT);
				purchaseOrderRepository.save(purchaseOrder);
				log.info("PurchaseOrder is created in service");
				break;

			} else {
				log.error("PurchaseOrder is not created in service");
				throw new CreateNewPurchaseOrderException("Supplier not found");

			}

		}

		return createNewPurchaseOrderDto;
	}

	@Override
	public String submitIndividualPurchaseOrder(String supplierName) throws SubmittedPurchaseOrderException {
		
		List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();

		if(purchaseOrderList.isEmpty())
		{
			throw new SubmittedPurchaseOrderException("purchaseOrderList is empty");
		}
		
		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			
			if (purchaseOrder.getSupplier().getSupplierName().equalsIgnoreCase(supplierName)) {
				
				if (purchaseOrder.getPurchaseOrderType().REGULAR != null || purchaseOrder.getPurchaseOrderType().SITETRANSFERIN != null) {
					
					purchaseOrder.setPoStatus(PoStatus.SUBMITTED);
					purchaseOrderRepository.save(purchaseOrder);
					log.info("PurchaseOrder is Submitted");
					return "Submitted";
					
				} else {
					log.error("PurchaseOrder is not Submitted");
					throw new SubmittedPurchaseOrderException("PurchaseOrder is not a regular/sitetransferin");
				}
			}
		}
		
		throw new SubmittedPurchaseOrderException("Supplier is not found");
		
		
	}


	@Override
	public String submitAllPurchaseOrder() throws SubmittedPurchaseOrderException {

		List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();

		if (purchaseOrderList.isEmpty())
			throw new SubmittedPurchaseOrderException("no purchaseOrderList is present");

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			if (purchaseOrder.getPoStatus().equals(PoStatus.DRAFT)) {
				purchaseOrder.setPoStatus(PoStatus.SUBMITTED);
				purchaseOrderRepository.save(purchaseOrder);
			}
		}

		return "Submitted All";
	}

	@Override
	public String approveIndividualPurchaseOrder(String supplierName) throws ApprovedPurchaseOrderException {

		List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();
		
		if (purchaseOrderList == null) {
			throw new ApprovedPurchaseOrderException("no purchaseOrderList is present");
		}

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			
			if (purchaseOrder.getSupplier().getSupplierName().equals(supplierName)) {
				
				if (purchaseOrder.getPurchaseOrderType().REGULAR != null || purchaseOrder.getPurchaseOrderType().SITETRANSFERIN != null && purchaseOrder.getPoStatus().equals(PoStatus.SUBMITTED)) {
					
					purchaseOrder.setPoStatus(PoStatus.APPROVED);
					purchaseOrderRepository.save(purchaseOrder);
					log.info("PurchaseOrder is Submitted");
					return "Approved";
					
				} else {
					log.error("PurchaseOrder is not Submitted");
					throw new ApprovedPurchaseOrderException("PurchaseOrder is not a regular/sitetransferin");
				}
			}
		}
		
		throw new ApprovedPurchaseOrderException("Supplier is not found");
	}

	@Override
	public String approveAllPurchaseOrder() throws ApprovedPurchaseOrderException {
		
		List<PurchaseOrder> purchaseOrderList = purchaseOrderRepository.findAll();

		if (purchaseOrderList.isEmpty())
			throw new ApprovedPurchaseOrderException("no purchaseOrderList is present");

		for (PurchaseOrder purchaseOrder : purchaseOrderList) {
			if (purchaseOrder.getPoStatus().equals(PoStatus.SUBMITTED)) {
				purchaseOrder.setPoStatus(PoStatus.APPROVED);
				purchaseOrderRepository.save(purchaseOrder);
			}
		}

		return "Approved All";
	}

}

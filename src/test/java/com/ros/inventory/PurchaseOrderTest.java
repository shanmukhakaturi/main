package com.ros.inventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ros.inventory.controller.PurchaseOrderController;
import com.ros.inventory.controller.dto.purchaseorder.BulkPurchaseOrderDto;
import com.ros.inventory.controller.dto.purchaseorder.CreateNewPurchaseOrderDto;
import com.ros.inventory.exception.ApprovedPurchaseOrderException;
import com.ros.inventory.exception.SubmittedPurchaseOrderException;
import com.ros.inventory.model.product.Product;
import com.ros.inventory.model.product.ProductCategory;
import com.ros.inventory.model.purchaseorder.OrderProduct;
import com.ros.inventory.model.purchaseorder.PoStatus;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.model.purchaseorder.PurchaseOrderType;
import com.ros.inventory.model.purchaseorder.UnitOfMeasurement;
import com.ros.inventory.model.supplier.Supplier;
import com.ros.inventory.model.supplier.SupplierType;
import com.ros.inventory.repository.OrderProductRepository;
import com.ros.inventory.repository.ProductRepository;
import com.ros.inventory.repository.PurchaseOrderRepository;
import com.ros.inventory.repository.SupplierRepository;
import com.ros.inventory.service.purchaseorder.PurchaseOrderService;
import com.ros.inventory.springsecurity.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =InventoryServiceApplication.class)
public class PurchaseOrderTest {


	@Autowired
	private PurchaseOrderService purchaseOrderService;

	@MockBean
	private SupplierRepository supplierRepository;

	@MockBean
	private PurchaseOrderRepository purchaseOrderRepository;

	@MockBean
	private UserRepository userRepository;

	@MockBean
	private ProductRepository productRepository;
	
	@MockBean
	private OrderProductRepository orderProductRepository;

	private static ObjectMapper mapper = new ObjectMapper();

	@Test
	public void createNewPurchaseOrder() throws Exception {
		// TODO Auto-generated method stub

		CreateNewPurchaseOrderDto createNewPurchaseOrderDto=new CreateNewPurchaseOrderDto();
		
		PurchaseOrder purchaseOrder =new PurchaseOrder();
		
		purchaseOrder.setCode("nfdhbc");
		purchaseOrder.setCreatedBy("vkmkmv");
        String sDate1="31/12/1998";  
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
		purchaseOrder.setCreatedDate(date1);
		purchaseOrder.setModifiedDate(date1);
		purchaseOrder.setModifiedBy("nndsnjn");
		purchaseOrder.setPoNumber(4545454);
		purchaseOrder.setPoStatus(PoStatus.DRAFT);
		purchaseOrder.setPurchaseOrderType(PurchaseOrderType.REGULAR);
		purchaseOrder.setTax(12.05);
		purchaseOrder.setTotal(151114);
		
		List<Supplier> suppliers;
		List<Product> Products;

		 when(suppliers=supplierRepository.findAll()).thenReturn(Stream.of(new Supplier(UUID.randomUUID(),"madhu","vjnn","njncnvn",date1,"nnfngf",date1,"njdncjnn",SupplierType.EXTERNAL)).collect(Collectors.toList()));
		 
		 when(Products=productRepository.findAll()).thenReturn(Stream.of(new Product(UUID.randomUUID(),"rice","bdbhbd","cncn",454,UnitOfMeasurement.KILOGRAM,55)).collect(Collectors.toList()));
		 
		 Iterator<Supplier> supplier =  suppliers.iterator();
		 Iterator<Product> product = Products.iterator();
		
		 while (supplier.hasNext() && product.hasNext() ) 
		 {
			 
			 supplier.next();
			 
			 OrderProduct orderProduct=new OrderProduct();
				
				orderProduct.setCode("hfafgufay");
				orderProduct.setCreatedBy("vfvfnfn");
				orderProduct.setLastModifiedBy("jnvfknjv");
				orderProduct.setName(product.next().getName());
				orderProduct.setPrice(5444);
				orderProduct.setProduct( product.next());
				orderProduct.setPurchaseOrder(purchaseOrder);
				orderProduct.setQuantity(40);
				orderProduct.setTax(11);
				orderProduct.setTotal(5454);
				orderProduct.setCreatedDate(date1);
				orderProduct.setLastModifiedDate(date1);
				
				purchaseOrder.setSupplier(supplier.next());
				purchaseOrderRepository.save(purchaseOrder);
				
			 assertEquals(createNewPurchaseOrderDto, purchaseOrderService.createNewPurchaseOrder(createNewPurchaseOrderDto));
			 
		 }
		 
		 
}
	
	@Test
	public void submitIndividualPurchaseOrder() throws Exception {
		
		String supplierName="madhu";
		 String sDate1="31/12/1998";  
	     Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	     
		List<Supplier> suppliers;
		List<PurchaseOrder> purchaseOrders;
		
		 when(suppliers=supplierRepository.findAll()).thenReturn(Stream.of(new Supplier(UUID.randomUUID(),"madhu","vjnn","njncnvn",date1,"nnfngf",date1,"njdncjnn",SupplierType.EXTERNAL)).collect(Collectors.toList()));
		when(purchaseOrders=purchaseOrderRepository.findAll()).thenReturn(Stream.of(new PurchaseOrder(UUID.randomUUID(),"bbcjb",111,11651651,56546,date1,"jncnnc",
				date1,"nkjcnjd",PurchaseOrderType.REGULAR,PoStatus.DRAFT)).collect(Collectors.toList()));
		
		Iterator<Supplier> supplier =  suppliers.iterator();
		 Iterator<PurchaseOrder> purchaseOrder = purchaseOrders.iterator();
		
		 while (supplier.hasNext() && purchaseOrder.hasNext() ) 
		 {
			 if(supplier.next().getSupplierName().equals(supplierName))
			 {
				 
			 purchaseOrder.next().setSupplier(supplier.next());
			 PurchaseOrder purchaseOrder1=purchaseOrder.next();
			 
			 if (purchaseOrder1.getPurchaseOrderType().REGULAR != null || purchaseOrder1.getPurchaseOrderType().SITETRANSFERIN != null) {
				 
				 purchaseOrder1.setPoStatus(PoStatus.SUBMITTED);
				 purchaseOrderRepository.save(purchaseOrder1);
				 
				 String expectedResult = "Submitted";
                  assertEquals(supplierName, purchaseOrderService.submitIndividualPurchaseOrder(supplierName));
				} 
			 }
		 }
	}
	
	
	@Test
	public void submitAllPurchaseOrder() throws Exception {
		
		List<PurchaseOrder> purchaseOrders;
		
		String sDate1="31/12/1998";  
	     Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	     
		when(purchaseOrders=purchaseOrderRepository.findAll()).thenReturn(Stream.of(new PurchaseOrder(UUID.randomUUID(),"bbcjb",111,11651651,56546,date1,"jncnnc",
				date1,"nkjcnjd",PurchaseOrderType.REGULAR,PoStatus.DRAFT)).collect(Collectors.toList()));
		
		Iterator<PurchaseOrder> purchaseOrder = purchaseOrders.iterator();
		 
		while (purchaseOrder.hasNext()) {
			
			if (purchaseOrder.next().getPoStatus().equals(PoStatus.DRAFT)) {
				
				purchaseOrder.next().setPoStatus(PoStatus.SUBMITTED);
				purchaseOrderRepository.save(purchaseOrder.next());
				 String expectedResult = "SubmittedAll";
				 assertEquals(expectedResult, purchaseOrderService.submitAllPurchaseOrder());
				 
			}
		}
		}
		
	@Test
	public void approveIndividualPurchaseOrder() throws Exception {
		
		String supplierName="madhu";
		 String sDate1="31/12/1998";  
	     Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	     
		List<Supplier> suppliers;
		List<PurchaseOrder> purchaseOrders;
		
		 when(suppliers=supplierRepository.findAll()).thenReturn(Stream.of(new Supplier(UUID.randomUUID(),"madhu","vjnn","njncnvn",date1,"nnfngf",date1,"njdncjnn",SupplierType.EXTERNAL)).collect(Collectors.toList()));
		when(purchaseOrders=purchaseOrderRepository.findAll()).thenReturn(Stream.of(new PurchaseOrder(UUID.randomUUID(),"bbcjb",111,11651651,56546,date1,"jncnnc",
				date1,"nkjcnjd",PurchaseOrderType.REGULAR,PoStatus.SUBMITTED)).collect(Collectors.toList()));
		
		Iterator<Supplier> supplier =  suppliers.iterator();
		 Iterator<PurchaseOrder> purchaseOrder = purchaseOrders.iterator();
		
		 while (supplier.hasNext() && purchaseOrder.hasNext() ) 
		 {
			 if(supplier.next().getSupplierName().equals(supplierName))
			 {
				 
			 purchaseOrder.next().setSupplier(supplier.next());
			 PurchaseOrder purchaseOrder1=purchaseOrder.next();
			 
			 if (purchaseOrder1.getPurchaseOrderType().REGULAR != null || purchaseOrder1.getPurchaseOrderType().SITETRANSFERIN != null) {
				 
				 purchaseOrder1.setPoStatus(PoStatus.APPROVED);
				 
				 String expectedResult = "Approved";
				 assertEquals(expectedResult, purchaseOrderService.approveIndividualPurchaseOrder(supplierName));

				} 
			 }
		 }
		
	}
	
	@Test
	public void approveAllPurchaseOrder() throws Exception {
		
		List<PurchaseOrder> purchaseOrders;
		
		String sDate1="31/12/1998";  
	     Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);  
	     
		when(purchaseOrders=purchaseOrderRepository.findAll()).thenReturn(Stream.of(new PurchaseOrder(UUID.randomUUID(),"bbcjb",111,11651651,56546,date1,"jncnnc",
				date1,"nkjcnjd",PurchaseOrderType.REGULAR,PoStatus.DRAFT)).collect(Collectors.toList()));
		
		Iterator<PurchaseOrder> purchaseOrder = purchaseOrders.iterator();
		 
		while (purchaseOrder.hasNext()) {
			
			if (purchaseOrder.next().getPoStatus().equals(PoStatus.SUBMITTED)) {
				
				purchaseOrder.next().setPoStatus(PoStatus.APPROVED);
				purchaseOrderRepository.save(purchaseOrder.next());
				
				 String expectedResult = "Approved All";
				 assertEquals(expectedResult, purchaseOrderService.submitAllPurchaseOrder());
				 
			}
		}
		}
	
}

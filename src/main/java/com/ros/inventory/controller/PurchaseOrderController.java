package com.ros.inventory.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ros.inventory.controller.dto.purchaseorder.BulkPurchaseOrderDto;
import com.ros.inventory.controller.dto.purchaseorder.CreateNewPurchaseOrderDto;
import com.ros.inventory.exception.ErrorHandler;
import com.ros.inventory.mapper.purchaseorder.CreateNewPurchaseOrderMapper;
import com.ros.inventory.model.purchaseorder.PurchaseOrder;
import com.ros.inventory.service.purchaseorder.PurchaseOrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin("*")
@Slf4j
public class PurchaseOrderController {

	@Autowired
	PurchaseOrderService purchaseOrderSerivce;

	@Operation(summary = "creating a new purchaseorder ")
	@RequestMapping(value = "/createnewpurchaseorder", method =  RequestMethod.POST)
	public ResponseEntity<?> createNewPurchaseOrder(
			@org.springframework.web.bind.annotation.RequestBody CreateNewPurchaseOrderDto createNewPurchaseOrderDto) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<>(
					purchaseOrderSerivce.createNewPurchaseOrder(createNewPurchaseOrderDto), HttpStatus.OK);
			    log.info("created new PurchaseOrder");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error(" PurchaseOrder is not created");
		}

		return response;

	}

	
	@Operation(summary="submit the individual purchaseorder")
	@RequestMapping(value = "/submitindividualpurchaseorder", method =  RequestMethod.PUT)
	public ResponseEntity<?> submitIndividualPurchaseOrder(@RequestParam String supplierName) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(
					purchaseOrderSerivce.submitIndividualPurchaseOrder(supplierName), HttpStatus.OK);
			    log.info("submitted the individual purchaseorder in controller");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("not submitted the individual purchaseorder in contoller ");
		}

		return response;

	}
	
	
	@Operation(summary="submit the all purchaseorder")
	@RequestMapping(value = "/submitAlljpurchaseorder", method =  RequestMethod.PUT)
	public ResponseEntity<?> submitAllPurchaseOrder() {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(
					purchaseOrderSerivce.submitAllPurchaseOrder(), HttpStatus.OK);
			    log.info("submitted all purchaseorders in controller");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("not submitted all purchaseorders in contoller ");
		}

		return response;

	}
	
	
	@Operation(summary="approve the individual purchaseorder")
	@RequestMapping(value = "/approveindividualpurchaseorder", method =  RequestMethod.PUT)
	public ResponseEntity<?> approveIndividualPurchaseOrder(@RequestParam String supplierName) {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(
					purchaseOrderSerivce.approveIndividualPurchaseOrder(supplierName), HttpStatus.OK);
			    log.info("approved the individual purchaseorder in controller");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("not approved the individual purchaseorder in contoller ");
		}

		return response;

	}
	
	@Operation(summary="approve the all purchaseorder")
	@RequestMapping(value = "/approveAlljpurchaseorder", method =  RequestMethod.PUT)
	public ResponseEntity<?> approveAllPurchaseOrder() {

		ResponseEntity<?> response;

		try {
			response = new ResponseEntity<String>(
					purchaseOrderSerivce.approveAllPurchaseOrder(), HttpStatus.OK);
			    log.info("approved all purchaseorders in controller");
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>(
					new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			 log.error("not approved all purchaseorders in contoller ");
		}

		return response;

	}

}

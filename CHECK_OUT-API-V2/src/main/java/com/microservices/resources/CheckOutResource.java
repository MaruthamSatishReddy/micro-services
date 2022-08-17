/**
 * 
 */
package com.microservices.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.entity.Purchase;
import com.microservices.entity.PurchaseResponse;
import com.microservices.service.CheckOutService;

/**
 * @author Satish Reddy
 *
 */
@RestController
@RequestMapping("/api/checkout")
public class CheckOutResource {

	private CheckOutService checkOutService;

	@Autowired
	public CheckOutResource(CheckOutService checkOutService) {
		this.checkOutService = checkOutService;
	}

	@PostMapping("/purchase")
	@CrossOrigin(origins = "http://localhost:4200")
	public PurchaseResponse placeOrder(@RequestBody Purchase purchase) {
		PurchaseResponse purchaseResponse = checkOutService.placeOrder(purchase);

		return purchaseResponse;
	}
}

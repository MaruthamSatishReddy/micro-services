/**
 * 
 */
package com.microservices.service;

import com.microservices.entity.Purchase;
import com.microservices.entity.PurchaseResponse;

/**
 * @author Satish Reddy
 *
 */
public interface CheckOutService {
	PurchaseResponse placeOrder(Purchase purchase);

}

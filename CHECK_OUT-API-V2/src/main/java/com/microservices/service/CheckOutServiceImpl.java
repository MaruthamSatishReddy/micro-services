package com.microservices.service;

import java.util.Set;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservices.entity.Customer;
import com.microservices.entity.Order;
import com.microservices.entity.OrderItem;
import com.microservices.entity.Purchase;
import com.microservices.entity.PurchaseResponse;
import com.microservices.repository.CustomerRepository;

@Service
public class CheckOutServiceImpl implements CheckOutService {

	private CustomerRepository customerRepository;

	@Autowired
	public CheckOutServiceImpl(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	@Override
	@Transactional
	public PurchaseResponse placeOrder(Purchase purchase) {
		Order order = purchase.getOrder();
		String orderTrackingId = generateTrackingId();
		order.setOrderTrackingId(orderTrackingId);
		Set<OrderItem> orderItems = purchase.getOrderItems();
		orderItems.forEach(item -> order.add(item));
		order.setBillingAddress(purchase.getBillingAddress());
		order.setShippingAddress(purchase.getShipingAddress());
		Customer customer = purchase.getCustomer();
		customer.add(order);
		customerRepository.save(customer);

		return new PurchaseResponse(orderTrackingId);
	}

	public String generateTrackingId() {
		return UUID.randomUUID().toString();
	}
}

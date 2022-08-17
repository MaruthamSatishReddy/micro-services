package com.easytobuy;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	@Autowired
	private FeignClientProxy feignClientProxy;
	
	@CircuitBreaker(name = "coupon-service",fallbackMethod = "fallbackGetCoupnDetails")
	public Optional<Coupon> getCoupnDetails(String productCode) {
		Optional<Coupon> getCoupnDetails = feignClientProxy.findByCouponCode(productCode);
		return getCoupnDetails;
	}

	@CircuitBreaker(name = "category-service",fallbackMethod = "fallbackGetCategoryDetails")
	public Optional<Category> getCategoryDetails(String categoryName) {
		Optional<Category> getCategoryDetail = feignClientProxy.findByCategoryName(categoryName);
		return getCategoryDetail;
	}
	
	public Optional<Coupon> fallbackGetCoupnDetails(String productCode) {
		Optional<Coupon> getCoupnDetails = Optional.empty();
		return getCoupnDetails;
	}
	
	public Optional<Category> fallbackGetCategoryDetails(String categoryName) {
		Optional<Category> getCategoryDetail = Optional.empty();
		return getCategoryDetail;
	}
	
}

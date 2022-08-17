package com.easytobuy;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "apigateway-server")
public interface FeignClientProxy {
@GetMapping("/coupon-service/api/coupon/couponCode/{couponCode}")
Optional<Coupon> findByCouponCode(@PathVariable String couponCode);

@GetMapping("/category-service/api/category/categoryName/{categoryName}")
Optional<Category> findByCategoryName(@PathVariable String categoryName);
}

package com.easytobuy;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CouponRepository  extends MongoRepository<Coupon,String>{
	
	Optional<Coupon> findByCouponCode(String couponCode);

}

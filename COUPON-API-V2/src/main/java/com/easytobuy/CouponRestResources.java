/**
 * 
 */
package com.easytobuy;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;

/**
 * @author Satish Reddy
 *
 */
@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponRestResources {
	@Autowired
	private final CouponRepository couponRepository;
	
	@Value("${server.port}")
	private int serverPort;

	@PostMapping
	public ResponseEntity<String> saveCoupon(@RequestBody Coupon coupon) {
		Coupon saveCoupon = couponRepository.insert(coupon);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(saveCoupon.getCouponId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/{id}")
	public Coupon getCouponById(@PathVariable String id) {
		return couponRepository.findById(id).orElseThrow(() -> new RuntimeException("Cannot Find Coupon By ID: " + id));
	}

	@GetMapping("/couponCode/{couponCode}")
	public ResponseEntity<Optional<Coupon>> getByCouponCode(@PathVariable("couponCode") String couponCode) {
		Optional<Coupon>couponDetail = couponRepository.findByCouponCode(couponCode);
		System.out.println("Load Balancer:::"+serverPort);
		if(couponDetail.isPresent()) {
			return new ResponseEntity<Optional<Coupon>>(couponDetail, HttpStatus.OK);
		}
		return new ResponseEntity<Optional<Coupon>>(couponDetail, HttpStatus.NO_CONTENT);
	}

	@GetMapping
	public List<Coupon> getCoupons() {
		System.out.println("serverPort::1"+serverPort);
		return couponRepository.findAll().stream().collect(Collectors.toList());

	}
	
}

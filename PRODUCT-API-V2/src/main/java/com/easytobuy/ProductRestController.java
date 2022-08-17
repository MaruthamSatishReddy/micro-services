
/**
 * 
 */
package com.easytobuy;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

/**
 * @author SatishReddy
 *
 */
@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CommonService commonService;

	@GetMapping
	// @CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> getAllProducts = productRepository.findAll().stream().collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(getAllProducts, HttpStatus.OK);

	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/category/{categoryId}")
    public List<Product> getByCategoryId(@PathVariable("categoryId") String categoryId) {
		List<Product> product = productRepository.findByCategoryId(categoryId);
		return product;
	}

	@PostMapping
	public ResponseEntity<Product> saveProduct(@RequestBody Product products) {
		try {
			Optional<Coupon> getCoupnDetails = commonService.getCoupnDetails(products.getCouponCode());
			Optional<Category> getCategoryDetail = commonService.getCategoryDetails(products.getCategoryId());
			if (getCoupnDetails.isPresent() && getCategoryDetail.isPresent()) {
				products.setProductPrice(products.getProductPrice().subtract(getCoupnDetails.get().getCouponPrice()));
				products.setCategoryId(getCategoryDetail.get().getCategoryId().toString());
				productRepository.save(products);
			}
			return new ResponseEntity<Product>(products, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<Product> couponProxy(@RequestBody Product products) {
		try {
			return new ResponseEntity<Product>(new Product(), HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<Product>(new Product(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/{productId}")
	public HttpStatus deleteProduct(@PathVariable("productId") String productId) {
		Optional<Product> Product = productRepository.findById(productId);
		if (Product.isPresent()) {
			productRepository.deleteById(productId);
			return HttpStatus.NO_CONTENT;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}

	@GetMapping("/{productId}")
	public List<Product> getByProductId(@PathVariable("productId") String productId) {
		List<Product> product = productRepository.findByProductId(productId);
		return product;
	}

	

}

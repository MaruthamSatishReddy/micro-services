/**
 * 
 */
package com.easytobuy;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Satish Reddy
 *
 */
@RestController
@RequestMapping("/api/rating")
@RequiredArgsConstructor
public class RatingRestController {
   @Autowired
	private RatingRepository ratingRepository;

	@PostMapping
	public ResponseEntity<Rating> saveCoupon(@RequestBody Rating rating) {
		try {
			ratingRepository.save(rating);
			return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping
	public ResponseEntity<List<Rating>> getAllRatings() {
		List<Rating> getAllRatings = ratingRepository.findAll().stream().collect(Collectors.toList());
		return new ResponseEntity<List<Rating>>(getAllRatings, HttpStatus.OK);

	}
}

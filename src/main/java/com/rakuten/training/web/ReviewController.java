package com.rakuten.training.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ProductService;
import com.rakuten.training.service.ReviewService;

@RestController
@RequestMapping("/products/{pid}/reviews")
public class ReviewController {
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Review>> getAllReviewsForAProduct(int pid) {
		Product p = productService.findById(pid);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(reviewService.findByProduct_Id(pid), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Review> addReviewToProduct(int pid, @RequestBody Review toBeAdded) {
		Product p = productService.findById(pid);
		if (p == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Review added = reviewService.addReviewToProduct(toBeAdded, pid);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(URI.create("/products/"+pid+"/reviews/"+added.getId()));
		return new ResponseEntity<>(added, headers, HttpStatus.CREATED);
	}
	
	
}

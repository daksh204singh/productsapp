package com.rakuten.training;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.rakuten.training.dal.ProductRepository;
import com.rakuten.training.domain.Product;
import com.rakuten.training.domain.Review;
import com.rakuten.training.service.ReviewService;

@SpringBootApplication
public class ProductsappApplication {

	public static void main(String[] args) {
		ApplicationContext springContainer =
				SpringApplication.run(ProductsappApplication.class, args);
		
//		testReviewAssciation(springContainer);
		
//		testSpringDataRepository(springContainer);
	}

	private static void testSpringDataRepository(ApplicationContext springContainer) {
		ProductRepository repo = springContainer.getBean(ProductRepository.class);
		Product p = new Product("repo", 10_000, 10);
		System.out.println("Saved a product with id: "+repo.save(p).getId());
	}

//	private static void testReviewAssciation(ApplicationContext springContainer) {
//		Review aReview = new Review("self", "very good", 5);
//		ReviewService svc = springContainer.getBean(ReviewService.class);
//		svc.addReviewToProduct(aReview, 1);
//	}

}
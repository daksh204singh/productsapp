package com.rakuten.training.web;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rakuten.training.domain.Product;
import com.rakuten.training.service.ProductService;

class WrongWayToTest {

	@Test
	void testGetProductById() {
		// Arrange
		ProductController objUnderTest = new ProductController();
		ProductService mockService = Mockito.mock(ProductService.class);
		int id = 1;
		Product returnedByMock = new Product("test", 8272, 12);
		returnedByMock.setId(id);	
		
		Mockito.when(mockService.findById(id)).thenReturn(returnedByMock);
		objUnderTest.service = mockService;
		
		// Act
		ResponseEntity<Product> response = objUnderTest.getProductById(id); 
		
		// Assert
		assertTrue(response.getBody().getId() == id);
		assertTrue(response.getStatusCode() == HttpStatus.OK);
	}
	

}

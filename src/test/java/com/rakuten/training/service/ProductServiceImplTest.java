package com.rakuten.training.service;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.rakuten.training.dal.ProductDAO;
import com.rakuten.training.domain.Product;

class ProductServiceImplTest {
	
	ProductServiceImpl objectUnderTest = new ProductServiceImpl();
	
	@Test
	void createNewProduct_Must_Return_Id_When_Product_Value_GTEQ_MinValue() {
		// AAA
		
		// Arrange
		ProductServiceImpl objUnderTest = new ProductServiceImpl();
		Product argToMethod = new Product("test", 10_000, 2);
		Product returnedByMethod = new Product("test", 10_000, 2);
		returnedByMethod.setId(1);
		
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.save(argToMethod)).thenReturn(returnedByMethod);
		objUnderTest.setDao(mockDAO);
		
		// Act
		int id = objUnderTest.createNewProduct(argToMethod);
		
		// Assert
		assertTrue(id > 0);
	}

	@Test
	void createNewProduct_Must_Throw_Exception_When_Product_Value_LT_MinValue() {
		// AAA
		
		// Arrange
		
		Product argToMethod = new Product("test", 5, 2);
		
		// Act & Assert
		Assertions
			.assertThatThrownBy(() -> objectUnderTest.createNewProduct(argToMethod))
			.isInstanceOf(IllegalArgumentException.class);
	}
	
	@Test
	void removeExistingProduct_Must_Delete_Product_When_Product_Value_LTEQ_100k() {
		// AAA
		
		// Arrange
		int id = 1;
		Product returnedByMock = new Product("test", 10000, 2);
		returnedByMock.setId(id);
		
		ProductDAO mockDAO = Mockito.mock(ProductDAO.class);
		Mockito.when(mockDAO.findById(id)).thenReturn(returnedByMock);
		objectUnderTest.setDao(mockDAO);
		
		// Act
		objectUnderTest.removeExisting(id);
		Mockito.verify(mockDAO).deleteById(id);
	}
	
}

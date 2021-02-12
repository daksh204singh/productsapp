package com.rakuten.training.dal;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.rakuten.training.domain.Product;

@Primary
@Repository
@Transactional
public class ProductDAOJPAImpl implements ProductDAO {

	@Autowired
	EntityManager em;
	
	@Override
	public Product save(Product toBeSaved) {
		em.persist(toBeSaved);
		return toBeSaved;
	}

	@Override
	public Product findById(int id) {
		return em.find(Product.class, id);
	}

	@Override
	public List<Product> findAll() {
		Query q = em.createQuery("SELECT p FROM Product as p");
		return q.getResultList();
	}
	
	@Override
	public void deleteById(int id) {
		Query q = em.createQuery("delete from Review r where r.product.id=:pid");
		q.setParameter("pid", id);
		int numReviewsDeleted = q.executeUpdate();
		System.out.println("<<<<< Deleted "+numReviewsDeleted+" reviews before deleting product with id "+id+" >>>>>>");
		Product p = em.find(Product.class, id);
		em.remove(p);
		
	}

}

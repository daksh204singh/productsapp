package com.rakuten.training.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "review")
public class Review {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	String reviewer;
	String review;
	float rating;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "p_id") // name is "the name of new foreign key column in review"
	Product product;
	
	public Review() {
		
	}
	
	public Review(String reviewer, String review, float rating) {
		super();
		this.reviewer = reviewer;
		this.review = review;
		this.rating = rating;
	}
	
	public String getReviewer() {
		return reviewer;
	}
	
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(String review) {
		this.review = review;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
}

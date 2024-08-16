package com.hibernate.persistence;

import java.util.List;

import com.hibernate.model.Product;

import jakarta.persistence.EntityManager;

public class ProductRepository {

	public static List<Product> getAll(EntityManager entityManager) {
		List<Product> list = entityManager.createQuery("select p from Product p",Product.class).getResultList();
		// TODO Auto-generated method stub
		return list;
	}

}

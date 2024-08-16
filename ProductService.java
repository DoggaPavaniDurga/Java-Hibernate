package com.hibernate.service;

import java.util.List;

import com.hibernate.exception.ResourecNotFoundException;
import com.hibernate.model.Product;
import com.hibernate.persistence.ProductRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class ProductService {
	
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;

	public ProductService(EntityManager em, EntityTransaction et) {
		// TODO Auto-generated constructor stub
		this.entityManager= em;
		this.entityTransaction=et;
		
	}

	public void save(Product p) {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		entityManager.persist(p);
		entityTransaction.commit();
		
	}

	public List<Product> getAll() {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		List<Product> list = ProductRepository.getAll(entityManager);
		entityTransaction.commit();
		return list;
	}

	public Product validateId(int id) throws ResourecNotFoundException {
		// TODO Auto-generated method stub
		entityTransaction.begin();

		Product p=entityManager.find(Product.class, id);
		try {
		if(p==null) 
			throw new ResourecNotFoundException("Invalis ID given..");
		}
		finally {
		entityTransaction.commit();
		}
		return p;
	}

	public void delete(Product prod) {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		entityManager.remove(prod);
		entityTransaction.commit();
	}

	public void update(Product prod) {
		// TODO Auto-generated method stub
		entityTransaction.begin();
		entityManager.persist(prod);
		entityTransaction.commit();
	}

}

package com.hibernate.controller;

import java.util.List;
import java.util.Scanner;

import com.hibernate.exception.ResourecNotFoundException;
import com.hibernate.model.Product;
import com.hibernate.service.ProductService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
public class ProductController {
	public static void main(String[] args) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("java_backend");
		//System.out.println("Works!!!");
		
		EntityManager entityManager= entityManagerFactory.createEntityManager();
		
		EntityTransaction entityTransaction=entityManager.getTransaction();
		Scanner sc = new Scanner(System.in);
		ProductService ps = new ProductService(entityManager,entityTransaction);
		
		while(true) {
		System.out.println("========hibernate Ops=========");
		System.out.println("1. Insert Product");
		System.out.println("2 . list all products");
		System.out.println("3 . delete product");
		System.out.println("4 . update product");
		System.out.println("0. Exist");
		int in = sc.nextInt();
		if(in==0) {
			System.out.println("Existing Bye....");
			break;
		}
		switch(in) {
		case 1:
			Product p = new Product();
			System.out.println("Enter product title: ");
			sc.nextLine();
			p.setTitle(sc.nextLine());
			System.out.println("Enter price :");
			p.setPrice(sc.nextDouble());
			System.out.println("Enter product description :");
			sc.nextLine();
			p.setDescription(sc.nextLine());
			ps.save(p);
			System.out.println("Product saved in DB...");
			break;
		case 2:
			List<Product> list = ps.getAll();
			list.stream().forEach(System.out::println);
			break;
		case 3:
			System.out.println("Enter product id :");
			int id = sc.nextInt();
			try {
				Product prod = ps.validateId(id);
				ps.delete(prod);
				System.out.println("Record Deleted..");
			} catch (ResourecNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;
		case 4:
			System.out.println("Enter product id :");
			id = sc.nextInt();
			try {
				Product prod = ps.validateId(id);
				System.out.println("Enter product title: ");
				sc.nextLine();
				prod.setTitle(sc.nextLine());
				System.out.println("Enter price :");
				prod.setPrice(sc.nextDouble());
				System.out.println("Enter product description :");
				sc.nextLine();
				prod.setDescription(sc.nextLine());
				ps.update(prod);
				System.out.println("Record updated..");
			} catch (ResourecNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
			break;
		default:
			System.out.println("Invalid input, Try Again");
		}
		}
	}

}

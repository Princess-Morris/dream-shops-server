package com.princess.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.princess.dream_shops.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}

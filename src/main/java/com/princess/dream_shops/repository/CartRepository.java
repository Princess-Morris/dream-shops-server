package com.princess.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.princess.dream_shops.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {


}

package com.princess.dream_shops.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.princess.dream_shops.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}

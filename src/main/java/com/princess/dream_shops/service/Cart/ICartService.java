package com.princess.dream_shops.service.Cart;

import java.math.BigDecimal;

import com.princess.dream_shops.model.Cart;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Long initializeNewCart()
}

package com.princess.dream_shops.service.Cart;

import com.princess.dream_shops.model.Cart;
import com.princess.dream_shops.model.CartItem;

public interface ICartIemService {
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuantity(Long cartId, Long productId, int quantity);
    CartItem getCartItem(Long cartId, Long productId);
}

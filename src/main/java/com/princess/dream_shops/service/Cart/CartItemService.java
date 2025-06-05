package com.princess.dream_shops.service.Cart;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.repository.CartItemRepository;
import com.princess.dream_shops.service.product.iProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartIemService {

    private final CartItemRepository cartItemRepository;
    private final iProductService productService;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity){
        //1. Get the cart
        // 2. Get the product
        //3. Check if the product is already in the requested quantity
        //4. If yes, then increase the quantity with the requested quanity
        // 5. If No, then initiate a new CartItem entry.
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId){

    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity){

    }

}

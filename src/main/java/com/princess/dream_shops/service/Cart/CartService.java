package com.princess.dream_shops.service.Cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.model.Cart;
import com.princess.dream_shops.model.CartItem;
import com.princess.dream_shops.repository.CartItemRepository;
import com.princess.dream_shops.repository.CartRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public Cart getCart(Long id){
        Cart cart = cartRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        return cartRepository.save(cart);
    }

    @Override
    public void clearCart(Long id){
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);

    }

    @Override
    public BigDecimal getTotalPrice(Long id){
        Cart cart = getCart(id);
         return cart.getItems()
         .stream()
         .map(CartItem :: getTotalPrice)
         .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}

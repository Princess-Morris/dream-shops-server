package com.princess.dream_shops.service.Cart;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.model.Cart;
import com.princess.dream_shops.model.CartItem;
import com.princess.dream_shops.model.Product;
import com.princess.dream_shops.repository.CartItemRepository;
import com.princess.dream_shops.repository.CartRepository;
import com.princess.dream_shops.service.category.iCategoryService;
import com.princess.dream_shops.service.product.iProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartIemService {

    private final CartItemRepository cartItemRepository;
    private final iProductService productService;
    private final ICartService cartService;
    private final CartRepository cartRepository;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity){
        //1. Get the cart
        // 2. Get the product
        //3. Check if the product is already in the requested quantity
        //4. If yes, then increase the quantity with the requested quanity
        // 5. If No, then initiate a new CartItem entry.

        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getItems()
        .stream()
        .filter(item -> item.getProduct().getId().equals(productId))
        .findFirst()
        .orElse(new CartItem()); 
        if(cartItem.getId() == null){
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId){
        Cart cart = cartService.getCart(cartId);
        CartItem itemToRemove = cart.getItems()
                    .stream()
                    .filter(item -> item.getProduct().getId().equals(productId))
                    .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        cart.removeItem(itemToRemove);            
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuantity(Long cartId, Long productId, int quantity){
        Cart cart = cartService.getCart(cartId);
        cart.getItems()
          .stream()
          .filter(item -> item.getProduct().getId().equals(productId))
          .findFirst()
          .ifPresent(item -> {
            item.setQuantity(quantity);
            item.setUnitPrice(item.getProduct().getPrice());
            item.setTotalPrice();
          });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);  
        cartRepository.save(cart);

    }

}

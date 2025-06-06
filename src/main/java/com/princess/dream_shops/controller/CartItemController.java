package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.Cart.ICartIemService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartIemService cartIemService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId,
                                                    @RequestParam Long productId,
                                                    @RequestParam Integer quantity){
      try{
        cartIemService.addItemToCart(cartId, productId, quantity);  
        return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
      } catch (ResourceNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
      }
                                                      
 }
}

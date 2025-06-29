package com.princess.dream_shops.controller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.princess.dream_shops.exceptions.ResourceNotFoundException;
import com.princess.dream_shops.response.ApiResponse;
import com.princess.dream_shops.service.Cart.ICartIemService;
import com.princess.dream_shops.service.Cart.ICartService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RequiredArgsConstructor
@RestController
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartIemService cartIemService;
    private ICartService cartService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false)  Long cartId,
                                                    @RequestParam Long productId,
                                                    @RequestParam Integer quantity){
      try{
        if(cartId == null){
            cartId = cartService.initializeNewCart();
        }
        cartIemService.addItemToCart(cartId, productId, quantity);  
        return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
      } catch (ResourceNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
      }
                                                      
 }

 @DeleteMapping("cart/{cartId}/item/{itemId}/remove")
 public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId){
    try{
         cartIemService.removeItemFromCart(cartId, itemId);
         return ResponseEntity.ok(new ApiResponse("Renove Item Success", null));
    } catch(ResourceNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(
            (e.getMessage()), null));
    }
 }

 @PutMapping("cart/{cartId}/item/{itemId}/update")
 public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId, 
                                                       @PathVariable Long itemId,
                                                        @RequestParam Integer quantity){

     try{
          cartIemService.updateItemQuantity(cartId, itemId, quantity);
          return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
     } catch(ResourceNotFoundException e){
        return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));

     }
 }
}

package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.model.Cart;
import com.springboot.shopping_cart.model.User;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.cart.ICartItemService;
import com.springboot.shopping_cart.service.cart.ICartService;
import com.springboot.shopping_cart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItem")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        User user = userService.getAuthenticatedUser();
        Cart cart = cartService.initializeNewCart(user);
        cartItemService.addItemToCart(cart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item added successfully!", null));
    }

    @DeleteMapping("/{cartId}/product/{productId}")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId,
                                                          @PathVariable Long productId) {
        cartItemService.removeItemFromCart(cartId, productId);
        return ResponseEntity.ok(new ApiResponse("Item removed successfully!", null));
    }

    @PutMapping("/{cartId}/product/{productId}")
    public ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                          @PathVariable Long productId,
                                                          @RequestParam Integer quantity) {
        cartItemService.updateItemQuantity(cartId, productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully!", null));
    }
}

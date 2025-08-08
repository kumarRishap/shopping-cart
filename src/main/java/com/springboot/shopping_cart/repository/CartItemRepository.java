package com.springboot.shopping_cart.repository;

import com.springboot.shopping_cart.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Transactional
    @Modifying
    long deleteAllByCartId(Long id);
}

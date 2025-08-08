package com.springboot.shopping_cart.data;

import com.springboot.shopping_cart.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository for managing Role entities.
 * Provides CRUD operations and a method to find a role by its name.
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}

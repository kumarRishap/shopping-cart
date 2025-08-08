package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.dto.UserDto;
import com.springboot.shopping_cart.model.User;
import com.springboot.shopping_cart.request.CreateUserRequest;
import com.springboot.shopping_cart.request.UpdateUserRequest;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    // ----------------- Public Endpoints -----------------

    /** Get a user by ID */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserDto userDto = userService.convertUserToDto(user);
        return ResponseEntity.ok(new ApiResponse("Success!", userDto));
    }

    // ----------------- Admin Endpoints -----------------
    // Can be secured later with @PreAuthorize("hasRole('ROLE_ADMIN')")

    /** Create a new user */
    @PostMapping
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        User newUser = userService.createUser(request);
        UserDto userDto = userService.convertUserToDto(newUser);
        return ResponseEntity.status(201).body(new ApiResponse("User added successfully!", userDto));
    }

    /** Update existing user */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {
        User updatedUser = userService.updateUser(request, id);
        UserDto userDto = userService.convertUserToDto(updatedUser);
        return ResponseEntity.ok(new ApiResponse("User updated successfully!", userDto));
    }

    /** Delete a user */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("User deleted successfully!", null));
    }
}

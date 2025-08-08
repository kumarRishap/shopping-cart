package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.dto.UserDto;
import com.springboot.shopping_cart.exception.AlreadyExistsException;
import com.springboot.shopping_cart.exception.ResourceNotFoundException;
import com.springboot.shopping_cart.model.User;
import com.springboot.shopping_cart.request.CreateUserRequest;
import com.springboot.shopping_cart.request.UpdateUserRequest;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("${api.prefix}/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/{id}/user")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {

        try {
            User user = userService.getUserById(id);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("success!", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest user) {

        try {
            User newUser = userService.createUser(user);
            UserDto userDto = userService.convertUserToDto(newUser);
            return ResponseEntity.ok(new ApiResponse("user added!", userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<ApiResponse> updateUser(@RequestParam UpdateUserRequest request, @PathVariable Long id) {

        try {
            User user = userService.updateUser(request, id);
            UserDto userDto = userService.convertUserToDto(user);
            return ResponseEntity.ok(new ApiResponse("user updated!", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {

        try {
            userService.deleteUser(id);
            return ResponseEntity.ok(new ApiResponse("user deleted!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

package com.springboot.shopping_cart.service.user;

import com.springboot.shopping_cart.dto.UserDto;
import com.springboot.shopping_cart.model.User;
import com.springboot.shopping_cart.request.CreateUserRequest;
import com.springboot.shopping_cart.request.UpdateUserRequest;

public interface IUserService{

    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}

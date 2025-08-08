package com.springboot.shopping_cart.data;

import com.springboot.shopping_cart.model.Role;
import com.springboot.shopping_cart.model.User;
import com.springboot.shopping_cart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN", "ROLE_USER");
        createDefaultRolesIfNotExist(defaultRoles);

        createDefaultUsers("ROLE_USER", "sam", 5);
        createDefaultUsers("ROLE_ADMIN", "admin", 2);
    }

    private void createDefaultRolesIfNotExist(Set<String> roles) {
        roles.stream()
                .filter(roleName -> roleRepository.findByName(roleName).isEmpty())
                .map(Role::new)
                .forEach(roleRepository::save);
    }

    private void createDefaultUsers(String roleName, String emailPrefix, int count) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new IllegalStateException(roleName + " not found in DB"));

        for (int i = 1; i <= count; i++) {
            String email = emailPrefix + i + "@email.com";
            if (userRepository.existsByEmail(email)) continue;

            User user = new User();
            user.setFirstName(roleName.equals("ROLE_ADMIN") ? "Admin" : "The User");
            user.setLastName(emailPrefix.equals("admin") ? "Admin" + i : "User" + i);
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("123456"));
            user.setRoles(Set.of(role));

            userRepository.save(user);
            System.out.println("Default " + roleName + " user " + i + " created successfully.");
        }
    }
}

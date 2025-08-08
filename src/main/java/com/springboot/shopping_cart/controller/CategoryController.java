package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.model.Category;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.category.ICategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final ICategoryService categoryService;

    // Get all categories
    @GetMapping
    public ResponseEntity<ApiResponse> getAllCategories() {
        return ResponseEntity.ok(new ApiResponse("Found!", categoryService.getAllCategories()));
    }

    // Add new category
    @PostMapping
    public ResponseEntity<ApiResponse> addCategory(@RequestBody Category category) {
        return ResponseEntity.ok(new ApiResponse("Success!", categoryService.addCategory(category)));
    }

    // Get category by ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("Found!", categoryService.getCategoryById(id)));
    }

    // Get category by Name
    @GetMapping("/search/{name}")
    public ResponseEntity<ApiResponse> getCategoryByName(@PathVariable String name) {
        return ResponseEntity.ok(new ApiResponse("Found!", categoryService.getCategoryByName(name)));
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(new ApiResponse("Deleted successfully!", null));
    }

    // Update category
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateCategory(@RequestBody Category category, @PathVariable Long id) {
        return ResponseEntity.ok(new ApiResponse("Update success!", categoryService.updateCategory(category, id)));
    }
}

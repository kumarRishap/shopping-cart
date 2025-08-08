package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.dto.ProductDto;
import com.springboot.shopping_cart.model.Product;
import com.springboot.shopping_cart.request.ProductAddRequest;
import com.springboot.shopping_cart.request.ProductUpdateRequest;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    // ----------------- Public Endpoints -----------------

    /** Get all products */
    @GetMapping
    public ResponseEntity<ApiResponse> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = productService.getConvertedProducts(products);
        return ResponseEntity.ok(new ApiResponse("Success!", productDtos));
    }

    /** Get products by name */
    @GetMapping("/search/by-name")
    public ResponseEntity<ApiResponse> getProductByName(@RequestParam String name) {
        List<Product> products = productService.getProductByName(name);
        return products.isEmpty()
                ? ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null))
                : ResponseEntity.ok(new ApiResponse("Success!", productService.getConvertedProducts(products)));
    }

    /** Get products by brand */
    @GetMapping("/search/by-brand")
    public ResponseEntity<ApiResponse> getProductByBrand(@RequestParam String brand) {
        List<Product> products = productService.getProductByBrand(brand);
        return products.isEmpty()
                ? ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null))
                : ResponseEntity.ok(new ApiResponse("Success!", productService.getConvertedProducts(products)));
    }

    /** Get products by category */
    @GetMapping("/search/by-category")
    public ResponseEntity<ApiResponse> getProductByCategory(@RequestParam String category) {
        List<Product> products = productService.getProductByCategory(category);
        return products.isEmpty()
                ? ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null))
                : ResponseEntity.ok(new ApiResponse("Success!", productService.getConvertedProducts(products)));
    }

    /** Get products by category and brand */
    @GetMapping("/search/by-category-and-brand")
    public ResponseEntity<ApiResponse> getProductByCategoryAndBrand(@RequestParam String category, @RequestParam String brand) {
        List<Product> products = productService.getProductByCategoryAndBrand(category, brand);
        return products.isEmpty()
                ? ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null))
                : ResponseEntity.ok(new ApiResponse("Success!", productService.getConvertedProducts(products)));
    }

    /** Get products by brand and name */
    @GetMapping("/search/by-brand-and-name")
    public ResponseEntity<ApiResponse> getProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        List<Product> products = productService.getProductByBrandAndName(brand, name);
        return products.isEmpty()
                ? ResponseEntity.status(NOT_FOUND).body(new ApiResponse("No product found!", null))
                : ResponseEntity.ok(new ApiResponse("Success!", productService.getConvertedProducts(products)));
    }

    /** Count products by brand and name */
    @GetMapping("/count/by-brand-and-name")
    public ResponseEntity<ApiResponse> countProductByBrandAndName(@RequestParam String brand, @RequestParam String name) {
        long count = productService.countProductByBrandAndName(brand, name);
        return ResponseEntity.ok(new ApiResponse("Products available: " + count, count));
    }

    // ----------------- Admin Endpoints -----------------
    // Require ROLE_ADMIN for modification operations
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse> addProduct(@RequestBody ProductAddRequest request) {
        Product product = productService.addProduct(request);
        return ResponseEntity.ok(new ApiResponse("Product added successfully!", productService.convertToDto(product)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{productId}")
    public ResponseEntity<ApiResponse> getProductById(@PathVariable Long productId) {
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Success!", productService.convertToDto(product)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequest request) {
        Product product = productService.updateProduct(request, productId);
        return ResponseEntity.ok(new ApiResponse("Product updated successfully!", productService.convertToDto(product)));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
        return ResponseEntity.ok(new ApiResponse("Product deleted successfully!", null));
    }
}

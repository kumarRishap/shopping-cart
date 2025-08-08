package com.springboot.shopping_cart.service.product;

import com.springboot.shopping_cart.dto.ProductDto;
import com.springboot.shopping_cart.model.Category;
import com.springboot.shopping_cart.model.Product;
import com.springboot.shopping_cart.request.ProductAddRequest;
import com.springboot.shopping_cart.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {

    Product addProduct(ProductAddRequest request);

    Product createProduct(ProductAddRequest request, Category category);

    Product getProductById(Long id);

    Product updateProduct(ProductUpdateRequest request, Long productId);

    Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request);

    void deleteProductById(Long id);

    List<Product> getAllProducts();

    List<Product> getProductByCategory(String category);

    List<Product> getProductByBrand(String brand);

    List<Product> getProductByCategoryAndBrand(String category, String brand);

    List<Product> getProductByName(String name);

    List<Product> getProductByBrandAndName(String brand, String name);

    Long countProductByBrandAndName(String brand, String name);

    List<ProductDto> getConvertedProducts(List<Product> products);

    ProductDto convertToDto(Product product);
}

package com.springboot.shopping_cart.service.product;

import com.springboot.shopping_cart.dto.ImageDto;
import com.springboot.shopping_cart.dto.ProductDto;
import com.springboot.shopping_cart.exception.ResourceNotFoundException;
import com.springboot.shopping_cart.model.Category;
import com.springboot.shopping_cart.model.Image;
import com.springboot.shopping_cart.model.Product;
import com.springboot.shopping_cart.repository.CategoryRepository;
import com.springboot.shopping_cart.repository.ProductRepository;
import com.springboot.shopping_cart.request.ProductAddRequest;
import com.springboot.shopping_cart.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public Product addProduct(ProductAddRequest request) {

        // Fetch category by ID
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));

        return productRepository.save(createProduct(request, category));
    }


    @Override
    public Product createProduct(ProductAddRequest request, Category category) {

        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product Not found!"));
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found!"));
    }

    @Override
    public Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {

        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        // Fetch category by ID from the request
        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found!"));
        existingProduct.setCategory(category);

        return existingProduct;
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () -> { throw new ResourceNotFoundException("Product not found!"); });
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }

    @Override
    public ProductDto convertToDto(Product product) {

        ProductDto productDto = modelMapper.map(product, ProductDto.class);
        List<Image> images = product.getImages(); // fixed: get images from product entity
        List<ImageDto> imageDto = images.stream()
                .map(image -> modelMapper.map(image, ImageDto.class))
                .toList();
        productDto.setImages(imageDto);

        return productDto;
    }
}

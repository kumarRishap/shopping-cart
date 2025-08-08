package com.springboot.shopping_cart.controller;

import com.springboot.shopping_cart.dto.ImageDto;
import com.springboot.shopping_cart.model.Image;
import com.springboot.shopping_cart.response.ApiResponse;
import com.springboot.shopping_cart.service.image.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/images")
@RequiredArgsConstructor
public class ImageController {

    private final IImageService imageService;

    // Upload multiple images for a product
    @PostMapping
    public ResponseEntity<ApiResponse> uploadImages(@RequestParam List<MultipartFile> files,
                                                    @RequestParam Long productId) {
        List<ImageDto> imageDtos = imageService.saveImages(files, productId);
        return ResponseEntity.ok(new ApiResponse("Upload success!", imageDtos));
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long imageId) {

        Image image = imageService.getImageById(imageId);
        ByteArrayResource resource = new ByteArrayResource(image.getImage()); // just use the byte[]

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + image.getFileName() + "\"")
                .body(resource);
    }


    // Update an existing image
    @PutMapping("/{imageId}")
    public ResponseEntity<ApiResponse> updateImage(@PathVariable Long imageId,
                                                   @RequestParam MultipartFile file) {
        imageService.updateImage(file, imageId);
        return ResponseEntity.ok(new ApiResponse("Update success!", null));
    }

    // Delete an image
    @DeleteMapping("/{imageId}")
    public ResponseEntity<ApiResponse> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImageById(imageId);
        return ResponseEntity.ok(new ApiResponse("Delete success!", null));
    }
}

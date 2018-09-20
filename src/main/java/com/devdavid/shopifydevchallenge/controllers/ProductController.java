package com.devdavid.shopifydevchallenge.controllers;

import com.devdavid.shopifydevchallenge.exceptions.ResourceNotFoundException;
import com.devdavid.shopifydevchallenge.models.Product;
import com.devdavid.shopifydevchallenge.repositories.ProductRepository;
import com.devdavid.shopifydevchallenge.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/shops/{shopId}/products")
    public List<Product> getAllProductsByShopId(@PathVariable(value = "shopId") Long shopId) {
        return productRepository.findByShopId(shopId);
    }

    @PostMapping("/shops/{shopId}/products")
    public Product createProduct(@PathVariable(value = "shopId") Long shopId,
                                 @Valid @RequestBody Product product) {
        return shopRepository.findById(shopId).map(shop -> {
            product.setShop(shop);
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }

    @PutMapping("/shops/{shopId}/products/{productId}")
    public Product updateProduct(@PathVariable Long shopId,
                                 @PathVariable Long productId,
                                 @Valid @RequestBody Product productRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return productRepository.findById(productId).map(product -> {
            product.setName(productRequest.getName());
            product.setPrice(productRequest.getPrice());
            return productRepository.save(product);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @DeleteMapping("/shops/{shopId}/products/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long shopId,
                                           @PathVariable Long productId,
                                           @Valid @RequestBody Product productRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return productRepository.findById(productId).map(product -> {
            productRepository.delete(product);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }
}

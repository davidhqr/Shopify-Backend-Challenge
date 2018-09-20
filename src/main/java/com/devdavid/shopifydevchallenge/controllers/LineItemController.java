package com.devdavid.shopifydevchallenge.controllers;

import com.devdavid.shopifydevchallenge.exceptions.ResourceNotFoundException;
import com.devdavid.shopifydevchallenge.models.LineItem;
import com.devdavid.shopifydevchallenge.models.Product;
import com.devdavid.shopifydevchallenge.repositories.LineItemRepository;
import com.devdavid.shopifydevchallenge.repositories.OrderRepository;
import com.devdavid.shopifydevchallenge.repositories.ProductRepository;
import com.devdavid.shopifydevchallenge.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class LineItemController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LineItemRepository lineItemRepository;

    @GetMapping("/shops/{shopId}/orders/{orderId}/line-items")
    public List<LineItem> getAllLineItemsByOrderId(@PathVariable(value = "shopId") Long shopId,
                                                   @PathVariable(value = "orderId") Long orderId) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return lineItemRepository.findByOrderId(orderId);
    }

    @GetMapping("/shops/{shopId}/products/{productId}/line-items")
    public List<LineItem> getAllLineItemsByProductId(@PathVariable(value = "shopId") Long shopId,
                                                     @PathVariable(value = "productId") Long productId) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return lineItemRepository.findByProductId(productId);
    }

    @PostMapping("/shops/{shopId}/orderAndProducts/{orderId}/{productId}/line-items")
    public LineItem createLineItemByOrderAndProductId(@PathVariable(value = "shopId") Long shopId,
                                                      @PathVariable(value = "orderId") Long orderId,
                                                      @PathVariable(value = "productId") Long productId,
                                                      @Valid @RequestBody LineItem lineItem) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        orderRepository.findById(orderId).map(order -> {
            lineItem.setOrder(order);
            return order;
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));

        return productRepository.findById(productId).map(product -> {
            lineItem.setProduct(product);
            return lineItemRepository.save(lineItem);
        }).orElseThrow(() -> new ResourceNotFoundException("ProductId " + productId + " not found"));
    }

    @PutMapping("/shops/{shopId}/orders/{orderId}/line-items/{lineItemId}")
    public LineItem updateLineItemByOrderId(@PathVariable Long shopId,
                                            @PathVariable Long orderId,
                                            @PathVariable Long lineItemId,
                                            @Valid @RequestBody LineItem lineItemRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("OrderId " + orderId + " not found");
        }

        return lineItemRepository.findById(lineItemId).map(lineItem -> {
            lineItem.setPrice(lineItemRequest.getPrice());
            lineItem.setQuantity(lineItemRequest.getQuantity());
            return lineItemRepository.save(lineItem);
        }).orElseThrow(() -> new ResourceNotFoundException("LineItemId " + lineItemId + " not found"));
    }

    @PutMapping("/shops/{shopId}/products/{productId}/line-items/{lineItemId}")
    public LineItem updateLineItemByProductId(@PathVariable Long shopId,
                                              @PathVariable Long productId,
                                              @PathVariable Long lineItemId,
                                              @Valid @RequestBody LineItem lineItemRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }

        return lineItemRepository.findById(lineItemId).map(lineItem -> {
            lineItem.setPrice(lineItemRequest.getPrice());
            lineItem.setQuantity(lineItemRequest.getQuantity());
            return lineItemRepository.save(lineItem);
        }).orElseThrow(() -> new ResourceNotFoundException("LineItemId " + lineItemId + " not found"));
    }

    @DeleteMapping("/shops/{shopId}/orders/{orderId}/line-items/{lineItemId}")
    public ResponseEntity<?> deleteLineItemByOrderId(@PathVariable Long shopId,
                                                     @PathVariable Long orderId,
                                                     @PathVariable Long lineItemId) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        if (!orderRepository.existsById(orderId)) {
            throw new ResourceNotFoundException("OrderId " + orderId + " not found");
        }

        return lineItemRepository.findById(lineItemId).map(lineItem -> {
            lineItemRepository.delete(lineItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("LineItemId " + lineItemId + " not found"));
    }

    @DeleteMapping("/shops/{shopId}/products/{productId}/line-items/{lineItemId}")
    public ResponseEntity<?> deleteLineItemByProductId(@PathVariable Long shopId,
                                                       @PathVariable Long productId,
                                                       @PathVariable Long lineItemId) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        if (!productRepository.existsById(productId)) {
            throw new ResourceNotFoundException("ProductId " + productId + " not found");
        }

        return lineItemRepository.findById(lineItemId).map(lineItem -> {
            lineItemRepository.delete(lineItem);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("LineItemId " + lineItemId + " not found"));
    }
}

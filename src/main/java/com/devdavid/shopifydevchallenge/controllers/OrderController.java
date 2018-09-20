package com.devdavid.shopifydevchallenge.controllers;

import com.devdavid.shopifydevchallenge.exceptions.ResourceNotFoundException;
import com.devdavid.shopifydevchallenge.models.Order;
import com.devdavid.shopifydevchallenge.models.Order;
import com.devdavid.shopifydevchallenge.repositories.OrderRepository;
import com.devdavid.shopifydevchallenge.repositories.OrderRepository;
import com.devdavid.shopifydevchallenge.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/shops/{shopId}/orders")
    public List<Order> getAllOrdersByShopId(@PathVariable(value = "shopId") Long shopId) {
        return orderRepository.findByShopId(shopId);
    }

    @PostMapping("/shops/{shopId}/orders")
    public Order createOrder(@PathVariable(value = "shopId") Long shopId,
                                 @Valid @RequestBody Order order) {
        return shopRepository.findById(shopId).map(shop -> {
            order.setShop(shop);
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }

    @PutMapping("/shops/{shopId}/orders/{orderId}")
    public Order updateOrder(@PathVariable Long shopId,
                                 @PathVariable Long orderId,
                                 @Valid @RequestBody Order orderRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            order.setTotalPrice(orderRequest.getTotalPrice());
            return orderRepository.save(order);
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));
    }

    @DeleteMapping("/shops/{shopId}/orders/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long shopId,
                                           @PathVariable Long orderId,
                                           @Valid @RequestBody Order orderRequest) {
        if (!shopRepository.existsById(shopId)) {
            throw new ResourceNotFoundException("ShopId " + shopId + " not found");
        }

        return orderRepository.findById(orderId).map(order -> {
            orderRepository.delete(order);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("OrderId " + orderId + " not found"));
    }
}

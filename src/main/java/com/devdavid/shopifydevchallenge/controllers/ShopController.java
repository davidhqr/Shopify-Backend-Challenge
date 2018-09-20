package com.devdavid.shopifydevchallenge.controllers;

import com.devdavid.shopifydevchallenge.exceptions.ResourceNotFoundException;
import com.devdavid.shopifydevchallenge.models.Shop;
import com.devdavid.shopifydevchallenge.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ShopController {

    @Autowired
    ShopRepository shopRepository;

    @GetMapping("/shops")
    public List<Shop> getAllShops() {
        return shopRepository.findAll();
    }

    @PostMapping("/shops")
    public Shop createShop(@Valid @RequestBody Shop shop) {
        return shopRepository.save(shop);
    }

    @PutMapping("/shops/{shopId}")
    public Shop updateShop(@PathVariable Long shopId, @Valid @RequestBody Shop shopRequest) {
        return shopRepository.findById(shopId).map(shop -> {
           shop.setName(shopRequest.getName());
           return shopRepository.save(shop);
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }

    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<?> deleteShop(@PathVariable Long shopId) {
        return shopRepository.findById(shopId).map(shop -> {
            shopRepository.delete(shop);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("ShopId " + shopId + " not found"));
    }
}

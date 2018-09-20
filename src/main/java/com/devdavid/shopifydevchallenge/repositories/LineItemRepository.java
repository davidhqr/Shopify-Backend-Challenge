package com.devdavid.shopifydevchallenge.repositories;

import com.devdavid.shopifydevchallenge.models.LineItem;
import com.devdavid.shopifydevchallenge.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineItemRepository extends JpaRepository<LineItem, Long> {
    List<LineItem> findByOrderId(Long orderId);
    List<LineItem> findByProductId(Long productId);
}

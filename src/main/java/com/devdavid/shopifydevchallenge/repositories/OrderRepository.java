package com.devdavid.shopifydevchallenge.repositories;

import com.devdavid.shopifydevchallenge.models.Order;
import com.devdavid.shopifydevchallenge.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByShopId(Long shopId);
}

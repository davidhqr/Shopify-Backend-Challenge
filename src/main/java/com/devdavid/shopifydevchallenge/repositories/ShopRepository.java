package com.devdavid.shopifydevchallenge.repositories;

import com.devdavid.shopifydevchallenge.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

}

package com.devdavid.shopifydevchallenge;

import com.devdavid.shopifydevchallenge.models.LineItem;
import com.devdavid.shopifydevchallenge.models.Order;
import com.devdavid.shopifydevchallenge.models.Product;
import com.devdavid.shopifydevchallenge.models.Shop;
import com.devdavid.shopifydevchallenge.repositories.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class ShopifyDevChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopifyDevChallengeApplication.class, args);
	}
}

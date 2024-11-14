package com.ecom.productservicejune24;

import com.ecom.productservicejune24.Models.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceJune24Application {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceJune24Application.class, args);

        Product product = new Product();
        product.setTitle("Apple iphone 15pro");
    }
}

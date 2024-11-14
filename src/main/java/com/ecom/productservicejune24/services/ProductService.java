package com.ecom.productservicejune24.services;

import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    List<Product> getAllProducts();

    Product updateProduct(Long productId, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    Product addNewProduct(Product product);
}

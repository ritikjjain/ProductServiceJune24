package com.ecom.productservicejune24.services;

import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundException;

    Page<Product> getAllProducts(int pageNumber, int pageSize);

    Product updateProduct(Long productId, Product product) throws ProductNotFoundException;

    Product replaceProduct(Long productId, Product product);

    void deleteProduct(Long productId);

    Product addNewProduct(Product product);
}

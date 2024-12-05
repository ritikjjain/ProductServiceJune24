package com.ecom.productservicejune24.services;

import com.ecom.productservicejune24.Models.Category;
import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;
import com.ecom.productservicejune24.repositories.CategoryRepository;
import com.ecom.productservicejune24.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        // Make a call to DB to fetch a product with given id

        //return productRepository.findById(productId); // wrong since not using optional
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product not found with id: " + productId );
        }
        Product product = optionalProduct.get();
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        // Sort sort = Sort.by("price").ascending().and(Sort.by("title").descending()); // IMP CONCEPT
       return  productRepository.findAll(
               PageRequest.of(pageNumber,
                       pageSize,
                       Sort.by("price").ascending().and(Sort.by("title").ascending()))
       );
    }

    //PATCH
    @Override
    public Product updateProduct(Long productId, Product product) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Product with id : " + productId + "doesn't exist");
        }
        Product productinDB = optionalProduct.get();
        // productinDB.set(product.getTitle()); // first check if title is null or not
        if(product.getTitle() != null){
            productinDB.setTitle(product.getTitle());
        }

        if (product.getPrice() != null){
            productinDB.setPrice(product.getPrice());
        }
        return productRepository.save(productinDB);
    }


    //PUT
    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Product addNewProduct(Product product) {
        Category category = product.getCategory();
//        if (category.getId() == null){
//            // We need to create a new category object in the DB first
//            category = categoryRepository.save(category);
//            product.setCategory(category);
//        }
        return productRepository.save(product);// imp
    }
}

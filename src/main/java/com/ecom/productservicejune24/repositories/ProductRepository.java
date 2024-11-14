package com.ecom.productservicejune24.repositories;

import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.projections.ProductWithIdAndTitle;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // Product Repo should contain all the methods (CRUD) related to Product Model.

    List<Product> findByPriceIsGreaterThan(Double price);
    List<Product> findProductByTitleLike(String word); // case sensitive
    List<Product> findByTitleLikeIgnoreCase(String word); // case insensitive
    List<Product> findTop5ByTitleContains(String word);
//  List<Product> findTopByTitleContainsIgnoreCase(int top, String word);
    List<Product> findProductByTitleContainsAndPriceGreaterThan(String word, Double price);
    Optional<Product> findById(Long id);

    @Override
    List<Product> findAll(Sort sort);

    //HQL
    @Query("select p.id as id, p.title as title from Product p where p.id = :x")
    List<ProductWithIdAndTitle> randomsearchMethod(Long x);

    //SQL
    @Query(value = "select * from product p where p.id = :productId", nativeQuery = true) // remember to add colon
    Product randomSearchMethod1(Long productId);
}

/*
1. Repository should be an interface.
2. Repository should extend JPA Repository.

Product p = productRepository.findById(100); // id 100 doesn't exist, so it will return null
Optional<Product> optionalProduct = productRepository.findById(100); // right way
String title = p.getTitle(); //NPE --> if you will not use optional product
*/
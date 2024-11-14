package com.ecom.productservicejune24;

import com.ecom.productservicejune24.Models.Category;
import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.projections.ProductWithIdAndTitle;
import com.ecom.productservicejune24.repositories.CategoryRepository;
import com.ecom.productservicejune24.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceJune24ApplicationTests {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

//    public ProductServiceJune24ApplicationTests(ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }

    @Test
    void contextLoads() {
    }

    @Test
    void testDBQueries(){
//        List<ProductWithIdAndTitle> productWithIdAndTitles =
//                productRepository.randomsearchMethod(1L);// since argument is long
//
//        for (ProductWithIdAndTitle product : productWithIdAndTitles){
//          System.out.println(product.getId() + " " + product.getTitle());
//        }

        Optional<Product> product = productRepository.findById(1L);

        Optional<Category> optionalCategory = categoryRepository.findById(1L); // use L always since Long data type

//        Category category = optionalCategory.get();
//
//        System.out.println("Getting Products");
//
//        List<Product> products = category.getProducts();

        System.out.println("DEBUG");
    }
}

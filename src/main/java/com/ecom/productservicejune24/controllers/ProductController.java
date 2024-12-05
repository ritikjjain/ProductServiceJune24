package com.ecom.productservicejune24.controllers;


import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;
import com.ecom.productservicejune24.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    public ProductController(@Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
        // throw new RuntimeException("Something went wrong");
//        ResponseEntity<Product> responseEntity = null;
//        try{
//            Product product =  productService.getSingleProduct(id);
//            responseEntity = new ResponseEntity<>(
//                    product,
//                    HttpStatus.OK
//            );
//        }catch(RuntimeException e){
//            responseEntity = new ResponseEntity<>(
////                    productService.getSingleProduct(id), // mistake
//                    HttpStatus.INTERNAL_SERVER_ERROR
//            );
//            // return responseEntity; // not to return here // mistake
//        }
//
////        ResponseEntity<Product> responseEntity =  new ResponseEntity<>(
////                productService.getSingleProduct(id),
////                HttpStatus.OK
////        );
        ResponseEntity<Product> response = new ResponseEntity<>(
                productService.getSingleProduct(id),
                HttpStatus.OK
        );
       return response;
    }

    @GetMapping()
    public Page<Product> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
        return productService.getAllProducts(pageNumber, pageSize);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id) {
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id,@RequestBody Product product) throws ProductNotFoundException {
        return productService.updateProduct(id, product);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        return null;
    }

//    @ExceptionHandler(ArithmeticException.class) // REM
//    public ResponseEntity<String> handleArithmeticException(){
//        ResponseEntity<String> response = new ResponseEntity<>(
//                "Arithmetic Exception has happened, Inside the product controller",
//                HttpStatus.NOT_FOUND
//        );
//        return response;
//    }

    @PostMapping("")
    public Product addNewProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }
}

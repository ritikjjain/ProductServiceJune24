package com.ecom.productservicejune24.services;
import com.ecom.productservicejune24.DTOs.FakeStoreProductDTOs;
import com.ecom.productservicejune24.Models.Category;
import com.ecom.productservicejune24.Models.Product;
import com.ecom.productservicejune24.configurations.ApplicationConfig;
import com.ecom.productservicejune24.exceptions.ProductNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
//@Primary // one method to remove ambiguity
public class FakeStoreProductService implements ProductService{

    private RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
        // throw new ArithmeticException();
        FakeStoreProductDTOs fakeStoreProductDTOs =
                restTemplate.getForObject("https://fakestoreapi.com/products/" + productId, FakeStoreProductDTOs.class);
        if (fakeStoreProductDTOs == null){
            throw new ProductNotFoundException("product with id " + productId + " doesn't exist");
        } // <-- was debugging here
        // convert FakeStoreProductDTOs into Product
//        Product product = new Product();
//        product.setId(fakeStoreProductDTOs.getId());
//        product.setTitle(fakeStoreProductDTOs.getTitle());
//        product.setPrice(fakeStoreProductDTOs.getPrice());
//        Category category = new Category();
//        category.setDescription(fakeStoreProductDTOs.getDescription());
//        product.setCategory(category); // IMP point
//        Abstracted out above code // Good Practice
        return convertFakeStoreProductDTOToProduct(fakeStoreProductDTOs);
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize) {
        FakeStoreProductDTOs[] fakeStoreProductDTOs = restTemplate.getForObject("https://fakestoreapi.com/products",
                FakeStoreProductDTOs[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProductDTOs fakeStoreProductDTO : fakeStoreProductDTOs) {
            products.add(convertFakeStoreProductDTOToProduct(fakeStoreProductDTO));
        }
        return new PageImpl<>(products);
    }

    private Product convertFakeStoreProductDTOToProduct(FakeStoreProductDTOs fakeStoreProductDTOs) {
        Product product = new Product();
        product.setId(fakeStoreProductDTOs.getId());
        product.setTitle(fakeStoreProductDTOs.getTitle());
        product.setPrice(fakeStoreProductDTOs.getPrice());
        Category category = new Category();
        category.setDescription(fakeStoreProductDTOs.getDescription());
        product.setCategory(category);
        return product;
    }

    @Override
    // Partial Update
    public Product updateProduct(Long productId, Product product) {
        // PATCH
        // restTemplate.put(); // Dont use APIs in APIs Bad Practice since return type of put is void and
        // you want to return the product
        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDTOs.class);
        HttpMessageConverterExtractor<FakeStoreProductDTOs> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDTOs.class,
                restTemplate.getMessageConverters());
        FakeStoreProductDTOs response =  restTemplate.execute("https://fakestoreapi.com/products/" + productId, HttpMethod.PATCH, requestCallback, responseExtractor);
        return convertFakeStoreProductDTOToProduct(response);
    }

    @Override
    public Product replaceProduct(Long productId, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long productId) {

    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }
}

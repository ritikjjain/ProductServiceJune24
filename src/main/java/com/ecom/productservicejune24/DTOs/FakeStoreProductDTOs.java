package com.ecom.productservicejune24.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDTOs {
    private Long id;
    private  String title;
    private double price;
    private String category;
    private String description;
    private String image;
}

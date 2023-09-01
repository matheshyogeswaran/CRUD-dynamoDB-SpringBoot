package com.crudDynamo.CrudDynamo.model;

import lombok.Data;

@Data
public class ProductDTO {

    private String id;
    private String name;
    private double price;
    private Long stockCount;

    // Other attributes if needed

}



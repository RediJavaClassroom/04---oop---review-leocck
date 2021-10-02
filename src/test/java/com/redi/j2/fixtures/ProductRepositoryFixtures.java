package com.redi.j2.fixtures;

import com.redi.j2.utils.ProductRepositoryProxy;

public class ProductRepositoryFixtures {

    public static ProductRepositoryProxy createProductRepository() {
        return new ProductRepositoryProxy();
    }
}

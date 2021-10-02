package com.redi.j2.fixtures;

import com.redi.j2.utils.ProductProxy;

import java.util.Optional;
import java.util.Random;

public class ProductFixtures {

    private static int ID = 0;
    private static final Random random = new Random();

    public static ProductProxy createProduct(String name, String brand, String category, Float price) {
        ID++;
        name = Optional.ofNullable(name).orElse("Product "+ID);
        brand = Optional.ofNullable(brand).orElse("Brand "+ID);
        category = Optional.ofNullable(category).orElse("Category "+ID);
        price = Optional.ofNullable(price).orElse(random.nextFloat()*200);
        return new ProductProxy(name, brand, category, price);
    }

    public static ProductProxy createProduct() {
        return createProduct(null, null, null, null);
    }
}

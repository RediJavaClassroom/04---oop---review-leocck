package com.redi.j2;

import java.util.*;
import java.util.stream.Collectors;

public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    public void addProduct(Product p) {
        if(!products.contains(p)) {
            products.add(p);
        }
    }

    public void removeProduct(Product p) {
        products.remove(p);
    }

    public Product getProductByName(String name) {
        Optional<Product> product = products.stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
        return product.orElse(null);
    }

    public List<Product> getProductsByCategory(String category) {
        return products.stream()
                .filter(p -> p.getCategory().equals(category))
                .sorted((p1, p2) -> {
                    if(p1.getAverageRating() > p2.getAverageRating()) return -1;
                    else return 1;
                })
                .collect(Collectors.toList());
    }
}

package com.redi.j2;

import com.redi.j2.fixtures.ProductFixtures;
import com.redi.j2.fixtures.ProductRepositoryFixtures;
import com.redi.j2.utils.ProductProxy;
import com.redi.j2.utils.ProductRepositoryProxy;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Step_8_ProductRepositoryTest {

    @Test
    void shouldOrderGetProductsByCategoryMethodResult() {

        // given - a ProductRepository with multiple rated products (from same category)
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        p1.addRating(3);
        ProductProxy p2 = ProductFixtures.createProduct(null, null, p1.getCategory(), null);
        p2.addRating(1);
        ProductProxy p3 = ProductFixtures.createProduct(null, null, p1.getCategory(), null);
        p3.addRating(5);
        ProductProxy p4 = ProductFixtures.createProduct(null, null, p1.getCategory(), null);
        p4.addRating(2);
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);
        ProductRepository.addProduct(p3);
        ProductRepository.addProduct(p4);

        // when - we get products by category
        List<ProductProxy> products = ProductRepository.getProductsByCategory(p1.getCategory());

        // then - the result list should be ordered by ratings
        assertNotNull(products, "Method should return a result list");
        assertEquals(4, products.size(), "Method should find all items with the same category");
        assertEquals(p3, products.get(0), "Product in position 0 is not ordered");
        assertEquals(p1, products.get(1), "Product in position 1 is not ordered");
        assertEquals(p4, products.get(2), "Product in position 2 is not ordered");
        assertEquals(p2, products.get(3), "Product in position 3 is not ordered");
    }
}

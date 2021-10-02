package com.redi.j2;

import com.redi.j2.fixtures.ProductFixtures;
import com.redi.j2.fixtures.ProductRepositoryFixtures;
import com.redi.j2.utils.ProductProxy;
import com.redi.j2.utils.ReflectionUtils;
import com.redi.j2.utils.ProductRepositoryProxy;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Step_5_ProductRepositoryTest {

    @Test
    void shouldCreateProductRepositoryClass() {

        // given - a class we want to find
        Class<?> ProductRepositoryClass;

        // when - we try to find the ProductRepository class
        ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // then - it should be found
        assertNotNull(ProductRepositoryClass, "Class com.redi.j2.ProductRepository must be defined");
    }

    @Test
    void shouldDeclareListOfProducts() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the 'products' property
        Field f = ReflectionUtils.getFieldForClass(ProductRepositoryClass, "products");

        // then - it should be found
        assertNotNull(f, "Property 'products' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'products' must be private");

        // and - its type to be List<String>
        assertTrue(ReflectionUtils.isListOf(ReflectionUtils.getProductClass(), f), "Property 'products' should have type List<Product>");
    }

    @Test
    void shouldNotHaveGetterForTheProductsProperty() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the getter for the 'products' property
        Method m = ReflectionUtils.getMethodForClass(ProductRepositoryClass, "getProducts");

        // then - it should not be found
        assertNull(m, "The property 'products' must not have a getter");
    }

    @Test
    void shouldHaveDefaultConstructor() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the default constructor
        Constructor<?> constructor = ReflectionUtils.getDefaultConstructor(ProductRepositoryClass);

        // then - it should be found
        assertNotNull(constructor, "The ProductRepository class should have a default no-args constructor");
    }

    @Test
    void shouldHaveAddProductMethod() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(ProductRepositoryClass, "addProduct", ReflectionUtils.getProductClass());

        // then - it should be found
        assertNotNull(m, "The method 'addProduct' must exist and receive a Product as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'addProduct' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "The method 'addProduct' should have return type void");
    }

    @Test
    void shouldAddProductToTheList() {

        // given - a ProductRepository
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();

        // and - some products we want to add to the ProductRepository
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();

        // when - we call the method to add these products
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);

        // then - the products are added to the list
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(2, products.size(), "The size of the list is wrong");
        assertTrue(products.contains(p1), "The '"+p1.getName()+"' was not found in the list");
        assertTrue(products.contains(p2), "The '"+p2.getName()+"' was not found in the list");
    }

    @Test
    void shouldNotAddSameProductToTheList() {

        // given - a ProductRepository with a product
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);

        // when - we try to add the same product
        ProductRepository.addProduct(p1);

        // then - the products is not added two times
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(1, products.size(), "The size of the list is wrong");
        assertTrue(products.contains(p1), "The '"+p1.getName()+"' was not found in the list");
    }

    @Test
    void shouldNotAddIdenticalProductToTheList() {

        // given - a ProductRepository with a product
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);

        // and - another product instance with exact same properties
        ProductProxy p2 = ProductFixtures.createProduct(p1.getName(), p1.getBrand(), p1.getCategory(), p1.getPrice());

        // when - we try to add the identical instance of the product
        ProductRepository.addProduct(p2);

        // then - the products is not added
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(1, products.size(), "The size of the list is wrong - the product should not be added (two instances might have the same properties, are you considering that?)");
        assertTrue(products.contains(p1), "The '"+p1.getName()+"' was not found in the list");
    }

    @Test
    void shouldHaveRemoveProductMethod() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(ProductRepositoryClass, "removeProduct", ReflectionUtils.getProductClass());

        // then - it should be found
        assertNotNull(m, "The method 'removeProduct' must exist and receive a Product as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'removeProduct' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "The method 'removeProduct' should have return type void");
    }

    @Test
    void shouldRemoveProductFromTheList() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);

        // when - we try to remove a product
        ProductRepository.removeProduct(p2);

        // then - the product is removed from the list
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(1, products.size(), "The size of the list is wrong");
        assertTrue(products.contains(p1), "The '"+p1.getName()+"' was not found in the list");

        // and - it is possible to remove all the tags
        ProductRepository.removeProduct(p1);
        assertEquals(0, ProductRepository.getProducts().size(), "After removing all products, list should be empty");
    }

    @Test
    void shouldNotRemoveNonExistingProduct() {

        // given - a ProductRepository with a product
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);

        // and - a product that was not added to the ProductRepository
        ProductProxy p2 = ProductFixtures.createProduct();

        // when - we try to remove this product
        ProductRepository.removeProduct(p2);

        // then - the list is not affected
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(1, products.size(), "The size of the list should remain the same");
        assertTrue(products.contains(p1), "The '"+p1.getName()+"' was not found in the list");
    }

    @Test
    void shouldRemoveIdenticalProducts() {

        // given - a ProductRepository with a product
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);

        // and - another product instance with exact same properties
        ProductProxy p2 = ProductFixtures.createProduct(p1.getName(), p1.getBrand(), p1.getCategory(), p1.getPrice());

        // when - we try to remove the identical instance of the product
        ProductRepository.removeProduct(p2);

        // then - the matching product is removed from the ProductRepository
        List<ProductProxy> products = ProductRepository.getProducts();
        assertEquals(0, products.size(), "The size of the list is wrong - the product should be removed (two instances might have the same properties, are you considering that?)");
    }

    @Test
    void shouldHaveGetProductByNameMethod() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(ProductRepositoryClass, "getProductByName", String.class);

        // then - it should be found
        assertNotNull(m, "The method 'getProductByName' must exist and receive a String as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'getProductByName' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(ReflectionUtils.getProductClass(), m), "The method 'getProductByName' should have return type Product");
    }

    @Test
    void shouldGetExistingProductByName() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);

        // when - we try to find a product by name
        ProductProxy product = ProductRepository.getProductByName(p2.getName());

        // then - the product is found
        assertNotNull(product, "The product should be found by name");
        assertEquals(p2, product, "The returned product must match the name");
    }

    @Test
    void shouldReturnNullIfProductIsNotFoundByName() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);

        // and - a product that was not added to the ProductRepository
        ProductProxy p2 = ProductFixtures.createProduct();

        // when - we try to find this product by name
        ProductProxy product = ProductRepository.getProductByName(p2.getName());

        // then - the product is not found
        assertNull(product, "The product should not be found");
    }

    @Test
    void shouldHaveGetProductsByCategoryMethod() {

        // given - the ProductRepository class
        Class<?> ProductRepositoryClass = ReflectionUtils.getProductRepositoryClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(ProductRepositoryClass, "getProductsByCategory", String.class);

        // then - it should be found
        assertNotNull(m, "The method 'getProductsByCategory' must exist and receive a String as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'getProductsByCategory' must be public");

        // and - its return type to be List<Product>
        assertTrue(ReflectionUtils.returnsListOf(ReflectionUtils.getProductClass(), m), "The method 'getProductsByTag' should have return type List<Product>");
    }

    @Test
    void shouldFindExistingProductByCategory() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);

        // when - we try to find a product by category
        List<ProductProxy> result = ProductRepository.getProductsByCategory(p2.getCategory());

        // then - the product is found
        assertNotNull(result, "The method should return a list");
        assertEquals(1, result.size(), "The size of the resulting list is not correct");
        assertTrue(result.contains(p2), "The returned list must contain objects with the specified category");
    }

    @Test
    void shouldFindMultipleProductsByCategory() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();
        ProductProxy p3 = ProductFixtures.createProduct(null, null, p1.getCategory(), null);
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);
        ProductRepository.addProduct(p3);

        // when - we try to find a product by category
        List<ProductProxy> result = ProductRepository.getProductsByCategory(p1.getCategory());

        // then - all products are found
        assertNotNull(result, "The method should return a list");
        assertEquals(2, result.size(), "The size of the resulting list is not correct");
        assertTrue(result.contains(p1), "The returned list must contain objects with the specified category");
        assertTrue(result.contains(p3), "The returned list must contain objects with the specified category");
    }

    @Test
    void shouldReturnEmptyListForNotExistentCategory() {

        // given - a ProductRepository with products
        ProductRepositoryProxy ProductRepository = ProductRepositoryFixtures.createProductRepository();
        ProductProxy p1 = ProductFixtures.createProduct();
        ProductProxy p2 = ProductFixtures.createProduct();
        ProductRepository.addProduct(p1);
        ProductRepository.addProduct(p2);

        // when - we try to find a product with a non existent category
        List<ProductProxy> result = ProductRepository.getProductsByCategory("Weird Category");

        // then - all products are found
        assertNotNull(result, "The method should still return a list");
        assertEquals(0, result.size(), "The size of the resulting list is not correct");
    }
}

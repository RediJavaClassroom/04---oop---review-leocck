package com.redi.j2;

import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class Step_3_ProductTest {

    @Test
    void shouldHaveGetterForTheNameProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the getter for the name property
        Method m = ReflectionUtils.getMethodForClass(productClass, getterNameFor("name"));

        // then - it should be found
        assertNotNull(m, "The property 'name' must have a getter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "Getter for the property 'name' must be public");

        // and - its return type to be String
        assertTrue(ReflectionUtils.hasReturnType(String.class, m), "Getter for the property 'name' should have return type String");

        // and - it should not have parameters
        assertFalse(ReflectionUtils.hasParameters(m), "Getter for the property 'name' should not have any parameters");
    }

    @Test
    void shouldImplementGetNameCorrectly() {

        // given - a product
        Object product = ReflectionUtils.instantiateProduct("Probiona Cultures Complex", "Nature Love", "Lactobacillus", 24.95f);
        String name = ReflectionUtils.getValueFromProperty(product, "name");

        // when - the getter is used
        String value = ReflectionUtils.invokeMethod(product, getterNameFor("name"));

        // then - the method returns the value of the property
        assertEquals(name, value, "The method getName must return the value from the property");
    }

    @Test
    void shouldHaveGetterForTheBrandProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the getter for the 'brand' property
        Method m = ReflectionUtils.getMethodForClass(productClass, getterNameFor("brand"));

        // then - it should be found
        assertNotNull(m, "The property 'brand' must have a getter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "Getter for the property 'brand' must be public");

        // and - its return type to be String
        assertTrue(ReflectionUtils.hasReturnType(String.class, m), "Getter for the property 'brand' should have return type String");

        // and - it should not have parameters
        assertFalse(ReflectionUtils.hasParameters(m), "Getter for the property 'brand' should not have any parameters");
    }

    @Test
    void shouldImplementGetBrandCorrectly() {

        // given - a product
        Object product = ReflectionUtils.instantiateProduct("Probiona Cultures Complex", "Nature Love", "Lactobacillus", 24.95f);
        String brand = ReflectionUtils.getValueFromProperty(product, "brand");

        // when - the getter is used
        String value = ReflectionUtils.invokeMethod(product, getterNameFor("brand"));

        // then - the method returns the value of the property
        assertEquals(brand, value, "The method getBrand must return the value from the property");
    }

    @Test
    void shouldHaveGetterForTheCategoryProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the getter for the 'category' property
        Method m = ReflectionUtils.getMethodForClass(productClass, getterNameFor("category"));

        // then - it should be found
        assertNotNull(m, "The property 'category' must have a getter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "Getter for the property 'category' must be public");

        // and - its return type to be String
        assertTrue(ReflectionUtils.hasReturnType(String.class, m), "Getter for the property 'category' should have return type String");

        // and - it should not have parameters
        assertFalse(ReflectionUtils.hasParameters(m), "Getter for the property 'category' should not have any parameters");
    }

    @Test
    void shouldImplementGetCategoryCorrectly() {

        // given - a product
        Object product = ReflectionUtils.instantiateProduct("Probiona Cultures Complex", "Nature Love", "Lactobacillus", 24.95f);
        String category = ReflectionUtils.getValueFromProperty(product, "category");

        // when - the getter is used
        String value = ReflectionUtils.invokeMethod(product, getterNameFor("category"));

        // then - the method returns the value of the property
        assertEquals(category, value, "The method getCategory must return the value from the property");
    }

    @Test
    void shouldHaveGetterForThePriceProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the getter for the 'price' property
        Method m = ReflectionUtils.getMethodForClass(productClass, getterNameFor("price"));

        // then - it should be found
        assertNotNull(m, "The property 'price' must have a getter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "Getter for the property 'price' must be public");

        // and - its return type to be float
        assertTrue(ReflectionUtils.hasReturnType(float.class, m), "Getter for the property 'price' should have return type float");

        // and - it should not have parameters
        assertFalse(ReflectionUtils.hasParameters(m), "Getter for the property 'price' should not have any parameters");
    }

    @Test
    void shouldImplementGetPriceCorrectly() {

        // given - a product
        Object product = ReflectionUtils.instantiateProduct("Probiona Cultures Complex", "Nature Love", "Lactobacillus", 24.95f);
        float price = ReflectionUtils.getValueFromProperty(product, "price");

        // when - the getter is used
        float value = ReflectionUtils.invokeMethod(product, getterNameFor("price"));

        // then - the method returns the value of the property
        assertEquals(price, value, "The method getPrice must return the value from the property");
    }

    @Test
    void shouldNotHaveDefaultGetterForTheTagsProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the getter for the 'tags' property
        Method m = ReflectionUtils.getMethodForClass(productClass, getterNameFor("tags"));

        // then - it should not be found
        assertNull(m, "The property 'tags' must not have a getter");
    }

    private String getterNameFor(String fieldName) {
        return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
    }
}

package com.redi.j2;

import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

public class Step_1_ProductTest {

    @Test
    void shouldHaveTheNameProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the name property
        Field f = ReflectionUtils.getFieldForClass(productClass, "name");

        // then - it should be found
        assertNotNull(f, "Property 'name' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'name' must be private");

        // and - its type to be String
        assertTrue(ReflectionUtils.hasType(String.class, f), "Property 'name' should have type String");
    }

    @Test
    void shouldHaveTheBrandProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the 'brand' property
        Field f = ReflectionUtils.getFieldForClass(productClass, "brand");

        // then - it should be found
        assertNotNull(f, "Property 'brand' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'brand' must be private");

        // and - its type to be String
        assertTrue(ReflectionUtils.hasType(String.class, f), "Property 'brand' should have type String");
    }

    @Test
    void shouldHaveTheCategoryProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the 'category' property
        Field f = ReflectionUtils.getFieldForClass(productClass, "category");

        // then - it should be found
        assertNotNull(f, "Property 'category' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'category' must be private");

        // and - its type to be String
        assertTrue(ReflectionUtils.hasType(String.class, f), "Property 'category' should have type String");
    }

    @Test
    void shouldHaveThePriceProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the 'price' property
        Field f = ReflectionUtils.getFieldForClass(productClass, "price");

        // then - it should be found
        assertNotNull(f, "Property 'price' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'price' must be private");

        // and - its type to be float
        assertTrue(ReflectionUtils.hasType(float.class, f), "Property 'price' should have type float");
    }

    @Test
    void shouldHaveTheTagsProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the 'tags' property
        Field f = ReflectionUtils.getFieldForClass(productClass, "tags");

        // then - it should be found
        assertNotNull(f, "Property 'tags' must be defined");

        // expect - the field to be private
        assertTrue(ReflectionUtils.isPrivate(f), "Property 'tags' must be private");

        // and - its type to be List<String>
        assertTrue(ReflectionUtils.isListOf(String.class, f), "Property 'tags' should have type List<String>");
    }
}

package com.redi.j2;

import com.redi.j2.fixtures.ProductFixtures;
import com.redi.j2.utils.ProductProxy;
import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

public class Step_8_ProductTest {

    @Test
    void shouldHaveAddRatingMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "addRating", int.class);

        // then - it should be found
        assertNotNull(m, "The method 'addRating' must exist and receive an int as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'addRating' must be public");

        // and - its return type to be List<Product>
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "The method 'addRating' should have return type void");
    }

    @Test
    void shouldHaveGetAmountRatingsMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "getAmountRatings");

        // then - it should be found
        assertNotNull(m, "A no-arg method 'getAmountRatings' must exist");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'getAmountRatings' must be public");

        // and - its return type to be List<Product>
        assertTrue(ReflectionUtils.hasReturnType(int.class, m), "The method 'getAmountRatings' should have return type int");
    }

    @Test
    void shouldHaveGetAmountRatingsMethodOverloadWithParameter() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "getAmountRatings", int.class);

        // then - it should be found
        assertNotNull(m, "The method 'getAmountRatings' must exist and receive an int as parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'getAmountRatings' must be public");

        // and - its return type to be List<Product>
        assertTrue(ReflectionUtils.hasReturnType(int.class, m), "The method 'getAmountRatings' should have return type int");
    }

    @Test
    void shouldHaveGetAverageRatingMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "getAverageRating");

        // then - it should be found
        assertNotNull(m, "The method 'getAverageRating' must exist and have no parameters");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'getAverageRating' must be public");

        // and - its return type to be List<Product>
        assertTrue(ReflectionUtils.hasReturnType(float.class, m), "The method 'getAverageRating' should have return type float");
    }

    @Test
    void shouldInitializeRatingAmount() {

        // given - a product without ratings
        ProductProxy product;

        // when - you instantiate it
        product = ProductFixtures.createProduct();

        // then - the rating amount should be zero
        assertEquals(0, product.getAmountRatings(), "A product must start with zero ratings");
    }

    @Test
    void shouldTrackRatingAmount() {

        // given - a product without ratings
        ProductProxy product = ProductFixtures.createProduct();

        // when - you add a rating
        product.addRating(5);

        // then - the rating is added to the product
        assertEquals(1, product.getAmountRatings(), "The rating amount must increase when you add a rating");

        // and - the amount increases also with multiple ratings
        product.addRating(4);
        product.addRating(5);
        assertEquals(3, product.getAmountRatings(), "The rating amount must increase when you add a rating");
    }

    @Test
    void shouldNotAcceptInvalidRatingValues() {

        // given - a product without ratings
        ProductProxy product = ProductFixtures.createProduct();

        // when - you add a not valid ratings
        product.addRating(6);
        product.addRating(-1);

        // then - these ratings are ignored
        assertEquals(0, product.getAmountRatings(), "The amount of ratings is wrong");
    }

    @Test
    void shouldInitializeAmountsPerRating() {

        // given - a product without ratings
        ProductProxy product;

        // when - you instantiate it
        product = ProductFixtures.createProduct();

        // then - the amounts per rating should be zero
        assertEquals(0, product.getAmountRatings(0), "A product must start with no 0 ratings");
        assertEquals(0, product.getAmountRatings(1), "A product must start with no 1 ratings");
        assertEquals(0, product.getAmountRatings(2), "A product must start with no 2 ratings");
        assertEquals(0, product.getAmountRatings(3), "A product must start with no 3 ratings");
        assertEquals(0, product.getAmountRatings(4), "A product must start with no 4 ratings");
        assertEquals(0, product.getAmountRatings(5), "A product must start with no 5 ratings");
    }

    @Test
    void shouldTrackAmountsPerRating() {

        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - you add multiple ratings to it
        product.addRating(5);
        product.addRating(5);
        product.addRating(4);
        product.addRating(3);
        product.addRating(2);
        product.addRating(2);
        product.addRating(2);

        // then - the rating amount should be correct for each individual rating
        assertEquals(2, product.getAmountRatings(5), "Amount of rating 5 does not match");
        assertEquals(1, product.getAmountRatings(4), "Amount of rating 4 does not match");
        assertEquals(1, product.getAmountRatings(3), "Amount of rating 3 does not match");
        assertEquals(3, product.getAmountRatings(2), "Amount of rating 2 does not match");
        assertEquals(0, product.getAmountRatings(1), "Amount of rating 1 does not match");
        assertEquals(0, product.getAmountRatings(0), "Amount of rating 0 does not match");
    }

    @Test
    void shouldIgnoreInvalidRatingWhenGettingAmount() {

        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - you call the method with an invalid rating
        int amount = product.getAmountRatings(20);

        // then - the rating amount should be correct for each individual rating
        assertEquals(0, amount, "Amount must be zero when an invalid rating is specified");
    }

    @Test
    void shouldInitializeAverageRatings() {

        // given - a product
        ProductProxy product;

        // when - you instantiate it
        product = ProductFixtures.createProduct();

        // then - the average should be -1
        assertEquals(-1, product.getAverageRating(), "When there are no ratings, the method should return -1");
    }

    @Test
    void shouldHaveCorrectAverageForSingleRating() {

        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - you add a single rating to it
        product.addRating(4);

        // then - the average should be the same value
        assertEquals(4.0f, product.getAverageRating(), "Average calculation is wrong when you have just one rating");
    }

    @Test
    void shouldHaveCorrectAverageForMultipleRatings() {

        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - you add a multiple ratings to it
        product.addRating(5);
        product.addRating(5);
        product.addRating(2);

        // then - the average should be correct
        assertEquals(4.0f, product.getAverageRating(), "Average calculation is wrong when you have multiple ratings");
    }

    @Test
    void shouldConsiderNewPropertiesInToString() {

        // given - a product with ratings
        ProductProxy product = ProductFixtures.createProduct();
        product.addRating(5);
        product.addRating(4);
        product.addRating(4);

        // when - we call the method
        String result = product.toString();

        // then - the result string contains the ratings
        assertTrue(result.contains("amountRatings"), "The 'amountRatings' information should be present in the String");
        assertTrue(result.contains("averageRating"), "The 'averageRating' information should be present in the String");
    }
}

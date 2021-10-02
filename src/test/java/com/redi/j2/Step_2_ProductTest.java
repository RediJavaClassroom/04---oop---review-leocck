package com.redi.j2;

import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Step_2_ProductTest {

    @Test
    void shouldHaveTheParametrizedConstructor() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we get a list of all constructors
        Constructor<?>[] constructors = ReflectionUtils.getAllConstructors(productClass);

        // then - there is only one
        assertEquals(1, constructors.length, "There should be only one constructor");

        // and - the parameters are exactly as defined in the exercise
        assertTrue(ReflectionUtils.hasExactParameterList(
                constructors[0],
                new Class[]{String.class, String.class, String.class, float.class}),
                "The constructor should receive parameters exactly as specified in the exercise (name, brand, category and price)");
    }

    @Test
    void shouldInitializeTheProperties() {

        // given - a Product
        Object product;

        // when - we create an instance
        product = ReflectionUtils.instantiateProduct("Bier Brau Set", "Brau Fasschen", "Home Brewing Kits", 42.9f);

        // then - the properties must be initialized from the constructor parameters
        assertEquals("Bier Brau Set", ReflectionUtils.getValueFromProperty(product, "name"), "Name property should be initialized");
        assertEquals("Brau Fasschen", ReflectionUtils.getValueFromProperty(product, "brand"), "Brand property should be initialized");
        assertEquals("Home Brewing Kits", ReflectionUtils.getValueFromProperty(product, "category"), "Category property should be initialized");
        assertEquals(42.9f, (float)ReflectionUtils.getValueFromProperty(product, "price"), "Price property should be initialized");

        // and - the list of tags must be empty
        List<String> tags = ReflectionUtils.getValueFromProperty(product, "tags");
        assertNotNull(tags, "The tags list must be initialized");
        assertTrue(tags.isEmpty(), "The tags list must start empty");
    }
}

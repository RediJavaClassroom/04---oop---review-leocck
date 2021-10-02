package com.redi.j2;

import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Step_0_ProductTest {

    @Test
    void shouldCreateProductClass() {

        // given - a class we want to find
        Class<?> productClass;

        // when - we try to find the Product class
        productClass = ReflectionUtils.getProductClass();

        // then - it should be found
        assertNotNull(productClass, "Class com.redi.j2.Product must be defined");
    }
}

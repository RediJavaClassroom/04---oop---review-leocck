package com.redi.j2;

import com.redi.j2.fixtures.ProductFixtures;
import com.redi.j2.utils.ProductProxy;
import com.redi.j2.utils.ReflectionUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class Step_4_ProductTest {

    @Test
    void shouldHaveSetterForThePriceProperty() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the setter for the 'price' property
        Method m = ReflectionUtils.getMethodForClass(productClass, "setPrice", float.class);

        // then - it should be found
        assertNotNull(m, "The property 'price' must have a setter that receives a float parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "Setter for the property 'price' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "Setter for the property 'price' should have return type void");
    }

    @Test
    void shouldAssignValueToPrice() {

        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - the setter is used
        float newValue = product.getPrice() / 2;
        product.setPrice(newValue);

        // then - the property is changed
        assertEquals(newValue, product.getPrice(), "Setter for the property 'price' must set the property to the specified value");
    }

    @Test
    void shouldNotAssignNegativeValuesToPrice() {

        // given - a product and its current price
        ProductProxy product = ProductFixtures.createProduct();
        float initialValue = product.getPrice();

        // when - the setter is used
        product.setPrice(-49.99f);

        // then - the property is changed
        assertEquals(initialValue, product.getPrice(), "Setter for the property 'price' should not set the price to a negative value");
    }

    @Test
    void shouldHaveAddTagMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "addTag", String.class);

        // then - it should be found
        assertNotNull(m, "The method 'addTag' must exist and receive a String parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'addTag' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "The method 'addTag' should have return type void");
    }

    @Test
    void shouldAddTagToAnEmptyList() {
        // given - a product
        ProductProxy product = ProductFixtures.createProduct();

        // when - we call the 'addTag' method
        product.addTag("christmas");

        // then - the tag is added to the list
        List<String> tags = product.getTags();
        assertEquals(1, tags.size(), "The size of the list is wrong");
        assertTrue(tags.contains("christmas"), "The specified tag was not found in the list");
    }

    @Test
    void shouldAddTagToNonEmptyList() {
        // given - a product with existing tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("discount");

        // when - we try to add another tag
        product.addTag("valentine");

        // then - the tag is added to the list
        List<String> tags = product.getTags();
        assertEquals(2, tags.size(), "The size of the list is wrong");
        assertTrue(tags.contains("discount"), "The old tag was not found in the list");
        assertTrue(tags.contains("valentine"), "The specified tag was not found in the list");
    }

    @Test
    void shouldNotAddDuplicatedTags() {
        // given - a product with existing tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("discount");

        // when - we try to add the same tag
        product.addTag("discount");

        // then - the tag is not added to the list
        List<String> tags = product.getTags();
        assertEquals(1, tags.size(), "The size of the list is wrong");
        assertTrue(tags.contains("discount"), "The specified tag was not found in the list");
    }

    @Test
    void shouldHaveHasTagMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "hasTag", String.class);

        // then - it should be found
        assertNotNull(m, "The method 'hasTag' must exist and receive a String parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'hasTag' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(boolean.class, m), "The method 'hasTag' should have return type boolean");
    }

    @Test
    void shouldSearchForTagsInsideTheList() {
        // given - a product with some tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("free");
        product.addTag("promotion");

        // when - we try to search for tags
        boolean existentTag1 = product.hasTag("free");
        boolean existentTag2 = product.hasTag("promotion");
        boolean absentTag = product.hasTag("discount");

        // then - existent tags are found
        assertTrue(existentTag1, "The 'free' tag should be found");
        assertTrue(existentTag2, "The 'promotion' tag should be found");

        // and - absent tags are not found
        assertFalse(absentTag, "The 'discount' tag should not be found");
    }

    @Test
    void shouldHaveRemoveTagMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "removeTag", String.class);

        // then - it should be found
        assertNotNull(m, "The method 'removeTag' must exist and receive a String parameter");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'removeTag' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(void.class, m), "The method 'removeTag' should have return type void");
    }

    @Test
    void shouldRemoveExistentTags() {
        // given - a product with some tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("free");
        product.addTag("promotion");

        // when - we try to remove a tag
        product.removeTag("promotion");

        // then - the tag is removed from the list
        assertEquals(1, product.getTags().size(), "The size of the list is not correct");
        assertFalse(product.getTags().contains("promotion"), "The 'promotion' tag should be removed from the list");
        assertTrue(product.getTags().contains("free"), "The 'free' tag should be still in the list");

        // and - it is possible to remove all the tags
        product.removeTag("free");
        assertEquals(0, product.getTags().size(), "After removing all tags, list should be empty");
    }

    @Test
    void shouldNotRemoveAbsentTags() {
        // given - a product with some tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("free");
        product.addTag("promotion");

        // when - we try to remove an alien tag
        product.removeTag("discount");

        // then - the tag list is not affected
        assertEquals(2, product.getTags().size(), "The size of the list is not correct");
        assertTrue(product.getTags().contains("promotion"), "The 'promotion' tag should be still in the list");
        assertTrue(product.getTags().contains("free"), "The 'free' tag should be still in the list");
    }

    @Test
    void shouldHaveToStringMethod() {

        // given - the Product class
        Class<?> productClass = ReflectionUtils.getProductClass();

        // when - we try to find the method
        Method m = ReflectionUtils.getMethodForClass(productClass, "toString");

        // then - it should be found
        assertNotNull(m, "The method 'toString' must exist and not receive any parameters");

        // expect - the method to be public
        assertTrue(ReflectionUtils.isPublic(m), "The method 'toString' must be public");

        // and - its return type to be void
        assertTrue(ReflectionUtils.hasReturnType(String.class, m), "The method 'toString' should have return type String");
    }

    @Test
    void shouldConsiderAllPropertiesInToString() {
        // given - a product with some tags
        ProductProxy product = ProductFixtures.createProduct();
        product.addTag("free");
        product.addTag("promotion");

        // when - we call the method
        String result = product.toString();

        // then - the result string contains all properties
        assertTrue(result.contains("name"), "The name of the property 'name' should be present in the String");
        assertTrue(result.contains("brand"), "The name of the property 'brand' should be present in the String");
        assertTrue(result.contains("category"), "The name of the property 'category' should be present in the String");
        assertTrue(result.contains("price"), "The name of the property 'price' should be present in the String");
        assertTrue(result.contains("tags"), "The name of the property 'tags' should be present in the String");
        assertTrue(result.contains(product.getName()), "The value of the property 'name' should be present in the String");
        assertTrue(result.contains(product.getBrand()), "The value of the property 'brand' should be present in the String");
        assertTrue(result.contains(product.getCategory()), "The value of the property 'category' should be present in the String");
        assertTrue(result.contains(product.getPrice() + ""), "The value of the property 'price' should be present in the String");
        assertTrue(result.contains(product.getTags().get(0)), "The tag '"+product.getTags().get(0)+"' should be present in the String");
        assertTrue(result.contains(product.getTags().get(1)), "The tag '"+product.getTags().get(1)+"' should be present in the String");
    }
}

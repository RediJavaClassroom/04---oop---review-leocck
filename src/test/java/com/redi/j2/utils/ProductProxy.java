package com.redi.j2.utils;

import java.util.Collection;
import java.util.List;

public class ProductProxy {

    private Object product;

    public ProductProxy(String name, String brand, String category, float price) {
        product = ReflectionUtils.instantiateProduct(name, brand, category, price);
    }

    public ProductProxy(Object product) {
        this.product = product;
    }

    public String getName() {
        if(product == null) {
            return null;
        }
        return ReflectionUtils.getValueFromProperty(product, "name");
    }

    public String getBrand() {
        if(product == null) {
            return null;
        }
        return ReflectionUtils.getValueFromProperty(product, "brand");
    }

    public String getCategory() {
        if(product == null) {
            return null;
        }
        return ReflectionUtils.getValueFromProperty(product, "category");
    }

    public float getPrice() {
        if(product == null) {
            return -1;
        }
        try {
            return ReflectionUtils.getValueFromProperty(product, "price");
        } catch (Exception e) {
            return -1;
        }
    }

    public void setPrice(float newValue) {
        if(product == null) {
            return;
        }
        ReflectionUtils.invokeMethod(product, "setPrice",
                new Class[] {float.class}, new Object[] { newValue });
    }

    public void addTag(String tag) {
        if(product == null) {
            return;
        }
        ReflectionUtils.invokeMethod(product, "addTag",
                new Class[] {String.class}, new Object[] { tag });
    }

    public Collection<String> getTags() {
        if(product == null) {
            return null;
        }
        return ReflectionUtils.getValueFromProperty(product, "tags");
    }

    public boolean hasTag(String tag) {
        if(product == null) {
            return false;
        }
        try {
            return ReflectionUtils.invokeMethod(product, "hasTag",
                    new Class[] {String.class}, new Object[] { tag });
        } catch (Exception e) {
            return false;
        }
    }

    public void removeTag(String tag) {
        if(product == null) {
            return;
        }
        try {
            ReflectionUtils.invokeMethod(product, "removeTag",
                    new Class[] {String.class}, new Object[] { tag });
        } catch (Exception e) {
        }
    }

    public Object getProduct() {
        return product;
    }

    public void addRating(int rating) {
        if(product == null) {
            return;
        }
        try {
            ReflectionUtils.invokeMethod(product, "addRating",
                    new Class[] {int.class}, new Object[] { rating });
        } catch(Exception e) { }
    }

    public int getAmountRatings() {
        if(product == null) {
            return -1;
        }
        try {
            return ReflectionUtils.invokeMethod(product, "getAmountRatings",
                    new Class[] {}, new Object[] {});
        } catch (Exception e) {
            return -1;
        }
    }

    public int getAmountRatings(int rating) {
        if(product == null) {
            return -1;
        }
        try {
            return ReflectionUtils.invokeMethod(product, "getAmountRatings",
                    new Class[] { int.class }, new Object[] { rating });
        } catch (Exception e) {
            return -1;
        }
    }

    public float getAverageRating() {
        if(product == null) {
            return -1;
        }
        try {
            return ReflectionUtils.invokeMethod(product, "getAverageRating",
                    new Class[] { }, new Object[] { });
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public String toString() {
        String str = product.toString();
        if(str == null) {
            return "";
        }
        return str;
    }

    @Override
    public boolean equals(Object obj) {
        boolean same = false;
        if(obj != null  && obj instanceof ProductProxy) {
            ProductProxy p = (ProductProxy) obj;
            same =  this.product.equals(p.getProduct());
        }
        return same;
    }

    @Override
    public int hashCode() {
        return product.hashCode();
    }
}

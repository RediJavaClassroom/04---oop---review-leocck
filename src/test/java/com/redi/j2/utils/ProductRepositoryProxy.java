package com.redi.j2.utils;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryProxy {

    private Object ProductRepository;
    private Class<?> productClass;

    public ProductRepositoryProxy() {
        ProductRepository = ReflectionUtils.instantiateProductRepository();
        productClass = ReflectionUtils.getProductClass();
    }

    public void addProduct(ProductProxy product) {
        if(ProductRepository == null) {
            return;
        }
        ReflectionUtils.invokeMethod(ProductRepository, "addProduct",
                new Class[] {productClass}, new Object[] { product.getProduct() });
    }

    public List<ProductProxy> getProducts() {
        if(ProductRepository == null) {
            return null;
        }
        List<Object> products = ReflectionUtils.getValueFromProperty(ProductRepository, "products");
        List<ProductProxy> result = new ArrayList<>();
        if(products != null) {
            for (Object product : products) {
                result.add(new ProductProxy(product));
            }
        }
        return result;
    }

    public void removeProduct(ProductProxy product) {
        if(ProductRepository == null) {
            return;
        }
        ReflectionUtils.invokeMethod(ProductRepository, "removeProduct",
                new Class[] {productClass}, new Object[] { product.getProduct() });
    }

    public ProductProxy getProductByName(String name) {
        if(ProductRepository == null) {
            return null;
        }
        ProductProxy result = null;
        Object product = ReflectionUtils.invokeMethod(ProductRepository, "getProductByName",
                new Class[] {String.class}, new Object[] { name });
        if(product != null) {
            result = new ProductProxy(product);
        }
        return result;
    }

    public List<ProductProxy> getProductsByCategory(String category) {
        if(ProductRepository == null) {
            return null;
        }
        List<ProductProxy> result = new ArrayList<>();
        List<Object> products = ReflectionUtils.invokeMethod(ProductRepository, "getProductsByCategory",
                new Class[] {String.class}, new Object[] { category });
        if(products != null) {
            for(Object p : products) {
                result.add(new ProductProxy(p));
            }
        }
        return result;
    }
}

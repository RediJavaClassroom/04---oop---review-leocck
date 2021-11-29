package com.redi.j2.utils;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.Class.*;

public class ReflectionUtils {

    public static Class<?> getProductClass() {
        try {
            return forName("com.redi.j2.Product");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Class<?> getProductRepositoryClass() {
        try {
            return forName("com.redi.j2.ProductRepository");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static Field getFieldForClass(Class<?> aClass, String propertyName) {
        if(aClass == null || propertyName == null) {
            return null;
        }
        try {
            return aClass.getDeclaredField(propertyName);
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static boolean isPrivate(Field field) {
        if(field == null) {
            return false;
        }
        return Modifier.isPrivate(field.getModifiers());
    }

    public static boolean isPublic(Method method) {
        if(method == null) {
            return false;
        }
        return Modifier.isPublic(method.getModifiers());
    }

    public static boolean hasType(Class<?> aClass, Field field) {
        if(aClass == null || field == null) {
            return false;
        }
        return field.getType().getName() == aClass.getName();
    }

    public static boolean isListOf(Class<?> aClass, Field field) {
        if(aClass == null || field == null) {
            return false;
        }
        Type type = field.getGenericType();
        if (field.getType() == List.class && type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            return pType.getActualTypeArguments()[0].getTypeName() == aClass.getTypeName();
        }
        return false;
    }

    public static boolean isCollectionOf(Class<?> aClass, Field field) {
        if(aClass == null || field == null) {
            return false;
        }
        Type type = field.getGenericType();
        if (Collection.class.isAssignableFrom(field.getType()) && type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            return pType.getActualTypeArguments()[0].getTypeName() == aClass.getTypeName();
        }
        return false;
    }

    public static boolean returnsListOf(Class<?> aClass, Method method) {
        if(aClass == null || method == null) {
            return false;
        }
        Type type = method.getGenericReturnType();
        if (method.getReturnType() == List.class && type instanceof ParameterizedType) {
            ParameterizedType pType = (ParameterizedType)type;
            return pType.getActualTypeArguments()[0].getTypeName() == aClass.getTypeName();
        }
        return false;
    }

    public static Method getMethodForClass(Class<?> aClass, String methodName, Class<?> ... parameterTypes) {
        try {
            return aClass.getDeclaredMethod(methodName, parameterTypes);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean hasReturnType(Class<?> aClass, Method method) {
        if(aClass == null || method == null) {
            return false;
        }
        return method.getReturnType() == aClass;
    }

    public static boolean hasParameters(Method method) {
        if(method == null) {
            return false;
        }
        return method.getParameterCount() > 0;
    }

    public static Constructor<?>[] getAllConstructors(Class<?> aClass) {
        if(aClass == null) {
            return new Constructor[]{};
        }
        return aClass.getConstructors();
    }

    public static Constructor<?> getDefaultConstructor(Class<?> aClass) {
        if(aClass == null) {
            return null;
        }
        try {
            return aClass.getConstructor();
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static boolean hasExactParameterList(Constructor<?> constructor, Class[] parameterClasses) {
        if(constructor == null || parameterClasses == null) {
            return false;
        }
        Class<?>[] parameterTypes = constructor.getParameterTypes();
        return Arrays.equals(parameterClasses, parameterTypes);
    }

    public static Object instantiateProduct(String name, String brand, String category, float price) {
        try {
            return getProductClass().getConstructors()[0].newInstance(name, brand, category, price);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object instantiateProductRepository() {
        try {
            return getProductRepositoryClass().getConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T getValueFromProperty(Object instance, String propertyName) {
        Field field = null;
        try {
            field = instance.getClass().getDeclaredField(propertyName);
            field.setAccessible(true);
            return (T) field.get(instance);
        } catch (Exception e) {
            return null;
        }
    }

    public static <T> T invokeMethod(Object object, String name){
        if(object == null || name == null) {
            return null;
        }
        return invokeMethod(object, name, new Class[]{}, new Object[]{});
    }

    public static <T> T invokeMethod(Object object, String name, Class<?>[] parameterTypes, Object[] parameterValues){
        Method method = null;
        try {
            method = object.getClass().getMethod(name, parameterTypes);
            method.setAccessible(true);
            return (T) method.invoke(object, parameterValues);
        } catch (Exception e) {
            return null;
        }
    }
}

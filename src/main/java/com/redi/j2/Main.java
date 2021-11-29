package com.redi.j2;

import java.util.List;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        // initializing the system
        ProductRepository repo = new ProductRepository();
        Product p1 = new Product("Chocolate", "Milka", "Sweets", 0.99f);
        Product p2 = new Product("TV", "Samsung", "Electronics", 199);
        repo.addProduct(p1);
        repo.addProduct(p2);

        // customer wants to find products by category
        List<Product> products = repo.getProductsByCategory("Electronics");
        for (Product p : products) {
            System.out.println(p);
        }

        // customers want to add ratings
        p1.addRating(5);
        p1.addRating(3);

        // first customer looks into the webpage
        System.out.println("first customer looks into the webpage");
        System.out.println(p1.getAmountRatings() + " Global Ratings");
        Float averageRating = (Float) SimpleCachingSystem.getInstance().getFromCache("avgRating_"+p1.getName());
        if(averageRating == null) {
            // cache-miss
            System.out.println("Cache-Miss");
            averageRating = p1.getAverageRating();
            SimpleCachingSystem.getInstance().addToCache("avgRating_"+p1.getName(), averageRating, 5);
        }
        System.out.println(averageRating + " out of 5");
        for(int i=1; i<=5; i++){
            System.out.println(i + " Stars: " + p1.getAmountRatings(i));
        }

        Thread.sleep(6000);

        // second customer looks into the webpage
        System.out.println("second customer looks into the webpage");
        System.out.println(p1.getAmountRatings() + " Global Ratings");
        averageRating = (Float) SimpleCachingSystem.getInstance().getFromCache("avgRating_"+p1.getName());
        if(averageRating == null) {
            // cache-miss
            System.out.println("Cache-Miss");
            averageRating = p1.getAverageRating();
            SimpleCachingSystem.getInstance().addToCache("avgRating_"+p1.getName(), averageRating, 5);
        }
        System.out.println(averageRating + " out of 5");
        for(int i=1; i<=5; i++){
            System.out.println(i + " Stars: " + p1.getAmountRatings(i));
        }
    }
}

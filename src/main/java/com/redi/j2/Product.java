package com.redi.j2;

import java.util.*;

public class Product {

    private String name;
    private String brand;
    private String category;
    private float price;
    private Set<String> tags;

    private List<Integer> ratings = new ArrayList<>();
    private Map<Integer, Integer> ratingsPerStar = new HashMap<>();
    private float currentAvg = -1;

    public Product(String name, String brand, String category, float price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        setPrice(price);
        tags = new HashSet<>();

        // Search - List Time Complexity O(N)
        // Search - Set Time Complexity O(1)
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float newPrice) {
        if(newPrice >= 0) {
            this.price = newPrice;
        }
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();

        buffer.append(this.getClass().getName() + " [");
        buffer.append("name=" + getName());
        buffer.append(", brand=" + getBrand());
        buffer.append(", category=" + getCategory());
        buffer.append(", price=" + getPrice());
        buffer.append(", tags=" + tags);
        buffer.append(", amountRatings=" + getAmountRatings());
        buffer.append(", averageRatings=" + getAverageRating());
        buffer.append("]");

        return buffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean same = false;
        if(obj != null && obj instanceof Product){
            Product p = (Product) obj;
            same =  (p.getName().equals(this.getName()) &&
                    p.getBrand().equals(this.getBrand()));
        }
        return same;
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() * this.getBrand().hashCode();
    }


    public void addRating(int stars) {
        if(stars < 0 || stars > 5) {
            return;
        }
        // storing the rating
        ratings.add(stars);
        // caching the amount of rating per star
        int amount = 0;
        if(ratingsPerStar.containsKey(stars)) {
            amount = ratingsPerStar.get(stars);
        }
        ratingsPerStar.put(stars, amount + 1);
        // calculate the new average
        currentAvg = (currentAvg*(ratings.size()-1)/ratings.size()) +
                ((float)stars/ratings.size());
    }

    public int getAmountRatings() {
        return ratings.size();
    }

    public int getAmountRatings(int stars) {
        if (ratingsPerStar.containsKey(stars)) {
            return ratingsPerStar.get(stars);
        }
        return 0;
    }

    public float getAverageRating() {
//        // Time Complexity O(N)
//        int counter = 0;
//        for (Integer rating : ratings) {
//            counter += rating;
//        }
//        float average = (float)counter / getAmountRatings();
//        return average;

        // Time Complexity O(1)
        return currentAvg;
    }
}

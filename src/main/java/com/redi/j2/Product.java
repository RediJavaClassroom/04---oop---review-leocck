package com.redi.j2;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private String name;
    private String brand;
    private String category;
    private float price;
    private List<String> tags;
    private int amountRatings;
    private int[] amountsPerRating;
    private float averageRating;

    public Product(String name, String brand, String category, float price) {
        this.name = name;
        this.brand = brand;
        this.category = category;
        setPrice(price);
        tags = new ArrayList<>();
        amountRatings = 0;
        amountsPerRating = new int[6];
        averageRating = -1;
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
        if(!tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public void addRating(int stars){
        if(stars < 0 || stars > amountsPerRating.length - 1) {
            return;
        }
        averageRating = (averageRating * amountRatings / (amountRatings+1)) +
                ((float)stars / (amountRatings+1));
        amountRatings++;
        amountsPerRating[stars]++;
    }

    public int getAmountRatings(){
        return amountRatings;
    }

    public int getAmountRatings(int stars){
        if(stars < 0 || stars > amountsPerRating.length - 1) {
            return 0;
        }
        return amountsPerRating[stars];
    }

    public float getAverageRating(){
        return averageRating;
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
        buffer.append(", amountRatings=" + amountRatings);
        buffer.append(", averageRating=" + averageRating);
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
}

package com.example.simplestoragesystem.service.model;

import com.example.simplestoragesystem.repository.CategoryRepository;
import lombok.Data;

import java.util.Objects;

import javax.persistence.*;

@Data
@Entity
@Table(name="Product")
public class Product {
    @Id @GeneratedValue private Long id;
    private String name;
    private int amount;
    private float price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;
    //private Producer producer; many-to-one (different producer means different object with possible different prices and amounts
    //private Storehouse storehouse; many-to-many? maybe as above?

    public Product() {
    }

    public Product(String name, int amount, float price, Category category) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
    }

    public Product(String name, int amount, float price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        //this.category =
    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public int getAmount() {
//        return amount;
//    }
//
//    public void setAmount(int amount) {
//        this.amount = amount;
//    }
//
//    public float getPrice() {
//        return price;
//    }
//
//    public void setPrice(float price) {
//        this.price = price;
//    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name) &&
                Objects.equals(this.amount, product.amount) && Objects.equals(this.price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.amount, this.price);
    }

    @Override
    public String toString() {
        return String.format("Product: id=%s, name=%s, amount=%s, price=%s", this.id, this.name, this.amount, this.price);
    }

    public void changeCategory (Category category) {
        this.category = category;
    }

}

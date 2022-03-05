package com.example.simplestoragesystem.model;

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
    @ManyToOne
    @JoinColumn(name="producer_id")
    private Producer producer;
    @ManyToOne
    @JoinColumn(name="storehouse_id")
    private Storehouse storehouse;

    public Product() {
    }

    public Product(String name, int amount, float price, Category category, Producer producer, Storehouse storehouse) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.category = category;
        this.producer = producer;
        this.storehouse = storehouse;
    }

    public Product(String name, int amount, float price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
        //this.category =
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product)) return false;
        Product product = (Product) obj;
        return Objects.equals(this.id, product.id) && Objects.equals(this.name, product.name) &&
                Objects.equals(this.amount, product.amount) && Objects.equals(this.price, product.price) &&
                Objects.equals(this.category, product.category) && Objects.equals(this.producer, product.producer) &&
                Objects.equals(this.storehouse, product.storehouse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.amount, this.price, this.category, this.producer, this.storehouse);
    }

    @Override
    public String toString() {
        return String.format("Product: id=%s, name=%s, amount=%s, price=%s, category=%s, producer=%s, storehouse=%s", this.id, this.name, this.amount, this.price, this.category, this.producer, this.storehouse);
    }

    public void changeCategory (Category category) {
        this.category = category;
    }

    public void changeProducer (Producer producer) {
        this.producer = producer;
    }

    public void changeStorehouse (Storehouse storehouse) {
        this.storehouse = storehouse;
    }

}

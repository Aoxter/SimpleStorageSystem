package com.example.simplestoragesystem.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Data
@Entity
public class Storehouse {
    private @Id @GeneratedValue Long id;
    private @Column(name="STOREHOUSE_NAME", length=250, nullable=false, unique=true) String name;
    //TODO: split on Town,Street, Post Code etc?
    private String address;
    @OneToMany
    private List<Product> products;

    public Storehouse() {
    }

    public Storehouse(String name, String address) {
        this.name = name;
        this.address = address;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Storehouse)) return false;
        Storehouse storehouse = (Storehouse) obj;
        return Objects.equals(this.id, storehouse.id) && Objects.equals(this.name, storehouse.name) && Objects.equals(this.address, storehouse.address);
    }

    @Override
    public String toString() {
        return String.format("Storehouse: id=%s, name=%s, address=%s", this.id, this.name, this.address);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void addProductsBulk(List<Product> products) {
        products.addAll(products);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public boolean checkProductsListEmpty() {return products.isEmpty();}
}

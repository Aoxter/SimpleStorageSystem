package com.example.simplestoragesystem.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Data //will generate getters and setters and constructors
@Entity
@Table(name="Category")
public class Category {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
    private @Column(name="CATEGORY_NAME", length=250, nullable=false, unique=true) String name;

    //@OneToMany(mappedBy="product")
    @OneToMany
    private List<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Category)) return false;
        Category category = (Category) obj;
        return Objects.equals(this.id, category.id) && Objects.equals(this.name, category.name);
    }

    @Override
    public String toString() {
        return String.format("Category: id=%s, name=%s", this.id, this.name);
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

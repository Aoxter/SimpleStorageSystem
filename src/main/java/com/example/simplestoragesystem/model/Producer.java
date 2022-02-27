package com.example.simplestoragesystem.model;

import lombok.Data;

import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Data
@Entity
public class Producer {
    private @Id @GeneratedValue Long id;
    private @Column(name="PRODUCER_NAME", length=250, nullable=false, unique=true) String name;
    private boolean isActive;
    //@OneToMany(mappedBy="product")
    @OneToMany
    private List<Product> products;

    public Producer() {
    }

    public Producer(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Producer)) return false;
        Producer producer = (Producer) obj;
        return Objects.equals(this.id, producer.id) && Objects.equals(this.name, producer.name);
    }

    @Override
    public String toString() {
        return String.format("Producer: id=%s, name=%s, active=%s", this.id, this.name, this.isActive);
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
}

package com.example.simplestoragesystem.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//The class requires a JPA @Table annotation changing the table’s name to CUSTOMER_ORDER because ORDER is not a valid name for table.
@Table(name = "CUSTOMER_ORDER")
public class Order {
    private @Id @GeneratedValue Long id;
    private String descripton;
    private Status status;

    public Order() {
    }

    public Order(String descripton, Status status) {
        this.descripton = descripton;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Order)) return false;
        Order order = (Order) obj;
        return Objects.equals(this.id, order.id) && Objects.equals(this.descripton, order.descripton) && this.status == order.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.descripton, this.status);
    }

    @Override
    public String toString() {
        return String.format("Order: id=%s, description=%s, status=%s", this.id, this.descripton, this.status);
    }
}

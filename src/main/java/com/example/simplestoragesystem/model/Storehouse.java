package com.example.simplestoragesystem.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Storehouse {
    private @Id @GeneratedValue Long id;
    private String name;
    //TODO: split on Town,Street, Post Code etc?
    private String address;

    public Storehouse() {
    }

    public Storehouse(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
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
}

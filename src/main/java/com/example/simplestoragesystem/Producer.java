package com.example.simplestoragesystem;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Producer {
    private @Id @GeneratedValue Long id;
    private String name;

    public Producer() {
    }

    public Producer(Long id, String name) {
        this.id = id;
        this.name = name;
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
        return String.format("Producer: id=%s, name=%s", this.id, this.name);
    }
}

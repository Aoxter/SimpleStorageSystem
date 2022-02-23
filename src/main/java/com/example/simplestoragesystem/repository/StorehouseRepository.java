package com.example.simplestoragesystem.repository;

import com.example.simplestoragesystem.service.model.Storehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorehouseRepository extends JpaRepository<Storehouse, Long> {
}

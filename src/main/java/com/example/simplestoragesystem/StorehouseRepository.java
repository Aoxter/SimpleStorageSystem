package com.example.simplestoragesystem;

import org.springframework.data.jpa.repository.JpaRepository;

interface StorehouseRepository extends JpaRepository<Storehouse, Long> {
}

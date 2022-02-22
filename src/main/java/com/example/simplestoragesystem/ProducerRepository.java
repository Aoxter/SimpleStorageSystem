package com.example.simplestoragesystem;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProducerRepository extends JpaRepository<Producer, Long> {
}

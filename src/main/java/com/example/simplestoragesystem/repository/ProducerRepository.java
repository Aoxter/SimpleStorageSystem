package com.example.simplestoragesystem.repository;

import com.example.simplestoragesystem.service.model.Producer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducerRepository extends JpaRepository<Producer, Long> {
}

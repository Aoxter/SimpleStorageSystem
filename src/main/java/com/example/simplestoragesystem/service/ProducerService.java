package com.example.simplestoragesystem.service;

import com.example.simplestoragesystem.exception.CategoryNotFoundException;
import com.example.simplestoragesystem.exception.ProducerNotFoundException;
import com.example.simplestoragesystem.exception.ProductHasAlreadyCategoryException;
import com.example.simplestoragesystem.exception.ProductHasAlreadyProducerException;
import com.example.simplestoragesystem.model.Category;
import com.example.simplestoragesystem.model.Producer;
import com.example.simplestoragesystem.model.Product;
import com.example.simplestoragesystem.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProducerService {
    private final ProducerRepository repository;
    private final ProductService productService;

    @Autowired
    public ProducerService(ProducerRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public Producer createProducer(Producer producer) {
        return repository.save(producer);
    }

    public List<Producer> readProducers() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Producer readProducer(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
    }

    @Transactional
    public Producer updateProducer(Long id, Producer producer) {
        Producer producerToEdit = readProducer(id);
        producerToEdit.setName(producer.getName());
        producerToEdit.setProducts(producer.getProducts());
        return  producerToEdit;
    }

    public Producer deleteProducer(Long id) {
        Producer producer = readProducer(id);
        repository.delete(producer);
        return producer;
    }

//    public Producer getDefaultProducer() {
//        return repository.findByName("Other");
//    }

    @Transactional
    public Producer addProductToProducer(Long productId, Long producerId) {
        Producer producer = readProducer(producerId);
        Product product = productService.readProduct(productId);
        if(Objects.nonNull(product.getProducer())) {
            throw new ProductHasAlreadyProducerException(productId, producerId);
        }
        producer.addProduct(product);
        return  producer;
    }

    @Transactional
    public Producer removeProductFromProducer(Long productId, Long producerId) {
        Producer producer = readProducer(producerId);
        Product product = productService.readProduct(productId);
        producer.removeProduct(product);
        return producer;
    }
}

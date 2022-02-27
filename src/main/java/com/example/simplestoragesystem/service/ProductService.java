package com.example.simplestoragesystem.service;

import com.example.simplestoragesystem.exception.ProductNotFoundException;
import com.example.simplestoragesystem.model.Category;
import com.example.simplestoragesystem.model.Producer;
import com.example.simplestoragesystem.model.Product;
import com.example.simplestoragesystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final CategoryService categoryService;
    private final ProducerService producerService;

    @Autowired
    public ProductService(ProductRepository repository, @Lazy CategoryService categoryService, @Lazy ProducerService producerService) {
        this.repository = repository;
        this.categoryService = categoryService;
        this.producerService = producerService;
    }

    public Product createProduct(Product product) {
        return repository.save(product);
    }

    public List<Product> readProducts() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Product readProduct(Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Transactional // using repository will replace repository.save()
    public Product updateProduct(Long id, Product product) {
        Product productToEdit = readProduct(id);
        productToEdit.setAmount(product.getAmount());
        //productToEdit.setCategory(product.getCategory());
        productToEdit.setPrice(product.getPrice());
        productToEdit.setName(product.getName());
        return  productToEdit;
    }

    public Product deleteProduct(Long id) {
        Product product = readProduct(id);
        repository.delete(product);
        return product;
    }

    @Transactional
    public Product updateCategory(Long productId, Long categoryId) {
        Product product = readProduct(productId);
        Category category = categoryService.readCategory(categoryId);
        product.changeCategory(category);
        return product;
    }

    @Transactional
    public Product updateProducer(Long productId, Long producerId) {
        Product product = readProduct(productId);
        Producer producer = producerService.readProducer(producerId);
        product.changeProducer(producer);
        return product;
    }
}

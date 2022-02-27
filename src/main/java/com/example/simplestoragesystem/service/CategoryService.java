package com.example.simplestoragesystem.service;

import com.example.simplestoragesystem.exception.CategoryNotFoundException;
import com.example.simplestoragesystem.exception.ProductHasAlreadyCategoryException;
import com.example.simplestoragesystem.model.Category;
import com.example.simplestoragesystem.model.Product;
import com.example.simplestoragesystem.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
    private final CategoryRepository repository;
    private final ProductService productService;

    @Autowired
    public CategoryService(CategoryRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public Category createCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> readCategories() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Category readCategory(Long id) {
        return repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Transactional
    public Category updateCategory(Long id, Category category) {
        Category categoryToEdit = readCategory(id);
        categoryToEdit.setName(category.getName());
        categoryToEdit.setProducts(category.getProducts());
        return  categoryToEdit;
    }

    public Category deleteCategory(Long id) {
        Category category = readCategory(id);
        repository.delete(category);
        return category;
    }

    public Category getDefaultCategory() {
        return repository.findByName("Other");
    }

    @Transactional
    public Category addProductToCategory(Long productId, Long categoryId) {
        Category category = readCategory(categoryId);
        Product product = productService.readProduct(productId);
        if(Objects.nonNull(product.getCategory())) {
            throw new ProductHasAlreadyCategoryException(productId, categoryId);
        }
        category.addProduct(product);
        return  category;
    }

    @Transactional
    public Category removeProductFromCategory(Long productId, Long categoryId) {
        Category category = readCategory(categoryId);
        Product product = productService.readProduct(productId);
        category.removeProduct(product);
        return category;
    }
}

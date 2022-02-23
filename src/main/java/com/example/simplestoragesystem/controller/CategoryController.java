package com.example.simplestoragesystem.controller;

import com.example.simplestoragesystem.assembler.CategoryModelAssembler;
import com.example.simplestoragesystem.service.model.Category;
import com.example.simplestoragesystem.service.CategoryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoryController {
    //private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;
    private final CategoryService service;

    public CategoryController(CategoryModelAssembler assembler, CategoryService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/categories")
    public CollectionModel<EntityModel<Category>> all() {
        //List<EntityModel<Category>> categories = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        List<EntityModel<Category>> categories = service.readCategories().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody final Category newCategory) {
        //return repository.save(newCategory);
        return service.createCategory(newCategory);
    }

    @GetMapping("/categories/{id}")
    public EntityModel<Category> single(@PathVariable final Long id) {
        //Category category = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        Category category = service.readCategory(id);
        return assembler.toModel(category);
    }

    @PutMapping("/category/{id}")
    public Category updateCategory(@RequestBody final Category newCategory, @PathVariable final Long id) {
//        return repository.findById(id).map(category -> {
//            category.setName(newCategory.getName());
//            return repository.save(category);
//        }).orElseGet(() -> {
//            newCategory.setId(id);
//            return repository.save(newCategory);
//        });
        return service.updateCategory(id, newCategory);
    }

    @DeleteMapping("/category/{id}")
    public Category deleteCategory(@PathVariable final Long id) {
        //repository.deleteById(id);
        return service.deleteCategory(id);
    }

    @PostMapping("/category/{categoryId}/products/{productId}/add")
    public Category addProductToCategory(@PathVariable final Long categoryId, @PathVariable final Long productId) {
        Category category = service.addProductToCategory(productId, categoryId);
        return category;
    }

    @DeleteMapping("/category/{categoryId}/products/{productId}/remove")
    public Category removeProductFromCategory(@PathVariable final Long categoryId, @PathVariable final Long productId) {
        Category category = service.removeProductFromCategory(productId, categoryId);
        return category;
    }
}

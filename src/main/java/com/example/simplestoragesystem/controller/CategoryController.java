package com.example.simplestoragesystem.controller;

import com.example.simplestoragesystem.assembler.CategoryModelAssembler;
import com.example.simplestoragesystem.exception.CategoryIsConnectedWithProductsException;
import com.example.simplestoragesystem.model.Category;
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
    private final CategoryModelAssembler assembler;
    private final CategoryService service;

    public CategoryController(CategoryModelAssembler assembler, CategoryService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/categories")
    public CollectionModel<EntityModel<Category>> all() {
        List<EntityModel<Category>> categories = service.readCategories().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @PostMapping("/categories")
    public Category createCategory(@RequestBody final Category newCategory) {
        return service.createCategory(newCategory);
    }

    @PostMapping("/categories/bulk")
    public List<Category> createCategoriesBulk(@RequestBody final List<Category> newCategories){
        return service.createCategoriesBulk(newCategories);
    }

    @GetMapping("/categories/{id}")
    public EntityModel<Category> single(@PathVariable final Long id) {
        Category category = service.readCategory(id);
        return assembler.toModel(category);
    }

    @PutMapping("/categories/{id}")
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

    @DeleteMapping("/categories/{id}")
    public Category deleteCategory(@PathVariable final Long id) {
        if(service.checkProductsList(id)) throw new CategoryIsConnectedWithProductsException(id);
        return service.deleteCategory(id);
    }

    @PostMapping("/categories/{categoryId}/products/{productId}/add")
    public Category addProductToCategory(@PathVariable final Long categoryId, @PathVariable final Long productId) {
        Category category = service.addProductToCategory(productId, categoryId);
        return category;
    }

    @DeleteMapping("/categories/{categoryId}/products/{productId}/remove")
    public Category removeProductFromCategory(@PathVariable final Long categoryId, @PathVariable final Long productId) {
        Category category = service.removeProductFromCategory(productId, categoryId);
        return category;
    }

    @PutMapping("/categories/{oldCategoryId}/moveTo/{newCategoryId}")
    public Category moveProductsBetweenCategories(@PathVariable final Long oldCategoryId, @PathVariable final Long newCategoryId) {
        Category category = service.moveProductsBetweenCategories(oldCategoryId, newCategoryId);
        return category;
    }
}

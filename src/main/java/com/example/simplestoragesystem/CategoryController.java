package com.example.simplestoragesystem;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class CategoryController {
    private final CategoryRepository repository;
    private final CategoryModelAssembler assembler;

    public CategoryController(CategoryRepository repository, CategoryModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/categories")
    CollectionModel<EntityModel<Category>> all() {
        List<EntityModel<Category>> categories = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(categories, linkTo(methodOn(CategoryController.class).all()).withSelfRel());
    }

    @PostMapping("/categories")
    Category newCategory(@RequestBody Category newCategory) { return repository.save(newCategory); }

    @GetMapping("/categories/{id}")
    EntityModel<Category> single(@PathVariable Long id) {
        Category category = repository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        return assembler.toModel(category);
    }

    @PutMapping("/category/{id}")
    Category replaceCategory(@RequestBody Category newCategory, @PathVariable Long id) {
        return repository.findById(id).map(category -> {
            category.setName(newCategory.getName());
            return repository.save(category);
        }).orElseGet(() -> {
            newCategory.setId(id);
            return repository.save(newCategory);
        });
    }

    @DeleteMapping("/category/{id}")
    void deleteCategory(@PathVariable Long id) { repository.deleteById(id); }
}

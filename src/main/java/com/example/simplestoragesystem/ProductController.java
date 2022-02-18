package com.example.simplestoragesystem;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {
    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/products")
    CollectionModel<EntityModel<Product>> all() {
        List<EntityModel<Product>> products = repository.findAll().stream().map(product -> EntityModel.of(product,
                linkTo(methodOn(ProductController.class).single(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("products")))
                .collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @PostMapping("/products")
    Product newProduct(@RequestBody Product newProduct){
        return repository.save(newProduct);
    }

    @GetMapping("/products/{id}")
    EntityModel<Product> single(@PathVariable Long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).single(id)).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("products"));
    }

    @PutMapping("/products/{id}")
    Product replaceProduct(@RequestBody Product newProduct, @PathVariable Long id){
        return repository.findById(id).map(product -> {
            product.setName(newProduct.getName());
            product.setAmount(newProduct.getAmount());
            product.setPrice(newProduct.getPrice());
            return repository.save(product);
        }).orElseGet(() -> {
            newProduct.setId(id);
            return repository.save(newProduct);
        });
    }

    @DeleteMapping("/products/{id}")
    void deleteProduct(@PathVariable Long id){
        repository.deleteById(id);
    }
}

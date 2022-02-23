package com.example.simplestoragesystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.simplestoragesystem.assembler.ProductModelAssembler;
import com.example.simplestoragesystem.service.model.Category;
import com.example.simplestoragesystem.service.model.Product;
import com.example.simplestoragesystem.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    //private final ProductRepository repository;
    private final ProductModelAssembler assembler;
    private final ProductService service;

    @Autowired
    public ProductController(ProductModelAssembler assembler, ProductService service) {
        this.assembler = assembler;
        this.service = service;
    }

    @GetMapping("/products")
    public CollectionModel<EntityModel<Product>> all() {
        //List<EntityModel<Product>> products = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        List<EntityModel<Product>> products = service.readProducts().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(products, linkTo(methodOn(ProductController.class).all()).withSelfRel());
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody final Product newProduct){
        return service.createProduct(newProduct);
    }

    @GetMapping("/products/{id}")
    public EntityModel<Product> single(@PathVariable final Long id) {
        //Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
        Product product = service.readProduct(id);
        return assembler.toModel(product);
    }

    @PutMapping("/products/{id}")
    public Product updateProduct(@RequestBody final Product newProduct, @PathVariable final Long id){
//        return repository.findById(id).map(product -> {
//            product.setName(newProduct.getName());
//            product.setAmount(newProduct.getAmount());
//            product.setPrice(newProduct.getPrice());
//            return repository.save(product);
//        }).orElseGet(() -> {
//            newProduct.setId(id);
//            return repository.save(newProduct);
//        });
        return service.updateProduct(id, newProduct);
    }

    @DeleteMapping("/products/{id}")
    public Product deleteProduct(@PathVariable final Long id){
        //repository.deleteById(id);
        return service.deleteProduct(id);
    }

    @PutMapping("/product/{productId}/category/{categoryId}/change")
    public Product updateProductCategory(@PathVariable final Long productId, @PathVariable final Long categoryId) {
        Product product = service.updateCategory(productId, categoryId);
        return product;
    }
}

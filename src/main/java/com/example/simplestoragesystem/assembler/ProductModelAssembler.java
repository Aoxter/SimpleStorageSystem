package com.example.simplestoragesystem.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.example.simplestoragesystem.controller.ProductController;
import com.example.simplestoragesystem.model.Product;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class ProductModelAssembler implements RepresentationModelAssembler<Product, EntityModel<Product>>{
    @Override
    public EntityModel<Product> toModel(Product product) {
        return EntityModel.of(product,
                linkTo(methodOn(ProductController.class).single(product.getId())).withSelfRel(),
                linkTo(methodOn(ProductController.class).all()).withRel("products"));
    }
}

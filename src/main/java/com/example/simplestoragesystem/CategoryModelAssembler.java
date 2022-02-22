package com.example.simplestoragesystem;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class CategoryModelAssembler implements RepresentationModelAssembler<Category, EntityModel<Category>> {
    @Override
    public EntityModel<Category> toModel(Category category) {
        return EntityModel.of(category,
                linkTo(methodOn(CategoryController.class).single(category.getId())).withSelfRel(),
                linkTo(methodOn(CategoryController.class).all()).withRel("categories"));
    }
}

package com.example.simplestoragesystem;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class StorehouseModelAssembler implements RepresentationModelAssembler<Storehouse, EntityModel<Storehouse>> {
    @Override
    public EntityModel<Storehouse> toModel(Storehouse storehouse) {
        return EntityModel.of(storehouse,
                linkTo(methodOn(StorehouseController.class).single(storehouse.getId())).withSelfRel(),
                linkTo(methodOn(StorehouseController.class).all()).withRel("storehouses"));
    }
}

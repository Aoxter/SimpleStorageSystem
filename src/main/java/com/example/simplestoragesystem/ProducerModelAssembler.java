package com.example.simplestoragesystem;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class ProducerModelAssembler implements RepresentationModelAssembler<Producer, EntityModel<Producer>> {
    @Override
    public EntityModel<Producer> toModel(Producer producer) {
        return EntityModel.of(producer,
                linkTo(methodOn(ProducerController.class).single(producer.getId())).withSelfRel(),
                linkTo(methodOn(ProducerController.class).all()).withRel("producers"));
    }
}

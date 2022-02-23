package com.example.simplestoragesystem.assembler;

import com.example.simplestoragesystem.controller.ProducerController;
import com.example.simplestoragesystem.service.model.Producer;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProducerModelAssembler implements RepresentationModelAssembler<Producer, EntityModel<Producer>> {
    @Override
    public EntityModel<Producer> toModel(Producer producer) {
        return EntityModel.of(producer,
                linkTo(methodOn(ProducerController.class).single(producer.getId())).withSelfRel(),
                linkTo(methodOn(ProducerController.class).all()).withRel("producers"));
    }
}

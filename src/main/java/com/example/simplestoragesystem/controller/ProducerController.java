package com.example.simplestoragesystem.controller;

import com.example.simplestoragesystem.assembler.ProducerModelAssembler;
import com.example.simplestoragesystem.exception.ProducerIsConnectedWithProductsException;
import com.example.simplestoragesystem.model.Producer;
import com.example.simplestoragesystem.service.ProducerService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProducerController {
    private final ProducerService service;
    private final ProducerModelAssembler assembler;

    public ProducerController(ProducerService service, ProducerModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/producers")
    public CollectionModel<EntityModel<Producer>> all() {
        List<EntityModel<Producer>> producers = service.readProducers().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(producers, linkTo(methodOn(ProducerController.class).all()).withSelfRel());
    }

    @PostMapping("/producers")
    public Producer createProducer(@RequestBody final Producer newProducer){
        return service.createProducer(newProducer);
    }

    @PostMapping("/producers/bulk")
    public List<Producer> createProducersBulk(@RequestBody final List<Producer> newProducers){
        return service.createProducersBulk(newProducers);
    }

    @GetMapping("/producers/{id}")
    public EntityModel<Producer> single(@PathVariable final Long id) {
        Producer producer = service.readProducer(id);
        return assembler.toModel(producer);
    }

    @PutMapping("/producers/{id}")
    public Producer updateProducer(@RequestBody final Producer newProducer, @PathVariable final Long id){
        return service.updateProducer(id, newProducer);
    }

    @DeleteMapping("/producers/{id}")
    public void deleteProducer(@PathVariable final Long id){
        if(service.checkProductsList(id)) throw new ProducerIsConnectedWithProductsException(id);
        service.deleteProducer(id);
    }

    @PostMapping("/producers/{producerId}/products/{productId}/add")
    public Producer addProductToProducer(@PathVariable final Long producerId, @PathVariable final Long productId) {
        Producer producer = service.addProductToProducer(productId, producerId);
        return producer;
    }

    @DeleteMapping("/producers/{producerId}/products/{productId}/remove")
    public Producer removeProductFromProducer(@PathVariable final Long producerId, @PathVariable final Long productId) {
        Producer producer = service.removeProductFromProducer(productId, producerId);
        return producer;
    }

    @PutMapping("/producers/{oldProducerId}/moveTo/{newProducerId}")
    public Producer moveProductsBetweenProducers(@PathVariable final Long oldProducerId, @PathVariable final Long newProducerId) {
        Producer producer = service.moveProductsBetweenProducers(oldProducerId, newProducerId);
        return producer;
    }
}

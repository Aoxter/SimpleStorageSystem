package com.example.simplestoragesystem;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProducerController {
    private final ProducerRepository repository;
    private final ProducerModelAssembler assembler;

    public ProducerController(ProducerRepository repository, ProducerModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/producers")
    CollectionModel<EntityModel<Producer>> all() {
        List<EntityModel<Producer>> producers = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(producers, linkTo(methodOn(ProducerController.class).all()).withSelfRel());
    }

    @PostMapping("/producers")
    Producer newProducer(@RequestBody Producer newProducer){
        return repository.save(newProducer);
    }

    @GetMapping("/producers/{id}")
    EntityModel<Producer> single(@PathVariable Long id) {
        Producer producer = repository.findById(id).orElseThrow(() -> new ProducerNotFoundException(id));
        return assembler.toModel(producer);
    }

    @PutMapping("/producers/{id}")
    Producer replaceProducer(@RequestBody Producer newProducer, @PathVariable Long id){
        return repository.findById(id).map(producer -> {
            producer.setName(newProducer.getName());
            return repository.save(producer);
        }).orElseGet(() -> {
            newProducer.setId(id);
            return repository.save(newProducer);
        });
    }

    @DeleteMapping("/producers/{id}")
    void deleteProducer(@PathVariable Long id){
        repository.deleteById(id);
    }
}

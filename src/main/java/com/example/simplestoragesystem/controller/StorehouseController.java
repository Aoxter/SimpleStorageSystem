package com.example.simplestoragesystem.controller;

import com.example.simplestoragesystem.assembler.StorehouseModelAssembler;
import com.example.simplestoragesystem.exception.StorehouseNotFoundException;
import com.example.simplestoragesystem.service.model.Storehouse;
import com.example.simplestoragesystem.repository.StorehouseRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StorehouseController {
    private final StorehouseRepository repository;
    private final StorehouseModelAssembler assembler;

    public StorehouseController(StorehouseRepository repository, StorehouseModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/storehouses")
    public CollectionModel<EntityModel<Storehouse>> all() {
        List<EntityModel<Storehouse>> storehouses = repository.findAll().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(storehouses, linkTo(methodOn(StorehouseController.class).all()).withSelfRel());
    }

    @PostMapping("/storehouses")
    public Storehouse createStorehouse(@RequestBody Storehouse newStorehouse) { return repository.save(newStorehouse); }

    @GetMapping("/storehouses/{id}")
    public EntityModel<Storehouse> single(@PathVariable Long id) {
        Storehouse storehouse = repository.findById(id).orElseThrow(() -> new StorehouseNotFoundException(id));
        return  assembler.toModel(storehouse);
    }

    @PutMapping("/storehouses/{id}")
    public Storehouse updateStorehouse(@RequestBody Storehouse newStorehouse, @PathVariable Long id) {
        return repository.findById(id).map(storehouse -> {
            storehouse.setName(newStorehouse.getName());
            storehouse.setAddress(newStorehouse.getAddress());
            return repository.save(storehouse);
        }).orElseGet(() -> {
            newStorehouse.setId(id);
            return repository.save(newStorehouse);
        });
    }

    @DeleteMapping("/storehouses/{id}")
    public void deleteStorehouse(@PathVariable Long id){
        repository.deleteById(id);
    }
}

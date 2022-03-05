package com.example.simplestoragesystem.controller;

import com.example.simplestoragesystem.assembler.StorehouseModelAssembler;
import com.example.simplestoragesystem.exception.StorehouseIsConnectedWithProductsException;
import com.example.simplestoragesystem.model.Storehouse;
import com.example.simplestoragesystem.service.StorehouseService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class StorehouseController {
    private final StorehouseService service;
    private final StorehouseModelAssembler assembler;

    public StorehouseController(StorehouseService service, StorehouseModelAssembler assembler) {
        this.service = service;
        this.assembler = assembler;
    }

    @GetMapping("/storehouses")
    public CollectionModel<EntityModel<Storehouse>> all() {
        List<EntityModel<Storehouse>> storehouses = service.readStorehouses().stream().map(assembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(storehouses, linkTo(methodOn(StorehouseController.class).all()).withSelfRel());
    }

    @PostMapping("/storehouses")
    public Storehouse createStorehouse(@RequestBody final Storehouse newStorehouse) {
        return service.createStorehouse(newStorehouse);
    }

    @PostMapping("/storehouses/bulk")
    public List<Storehouse> createStorehousesBulk(@RequestBody final List<Storehouse> newStorehouses){
        return service.createStorehousesBulk(newStorehouses);
    }

    @GetMapping("/storehouses/{id}")
    public EntityModel<Storehouse> single(@PathVariable final Long id) {
        Storehouse storehouse = service.readStorehouse(id);
        return  assembler.toModel(storehouse);
    }

    @PutMapping("/storehouses/{id}")
    public Storehouse updateStorehouse(@RequestBody final Storehouse newStorehouse, @PathVariable final Long id) {
        return service.updateStorehouse(id, newStorehouse);
    }

    @DeleteMapping("/storehouses/{id}")
    public Storehouse deleteStorehouse(@PathVariable final Long id){
        if(service.checkProductsList(id)) throw new StorehouseIsConnectedWithProductsException(id);
        return service.deleteStorehouse(id);
    }

    @PostMapping("/storehouses/{storehouseId}/products/{productId}/add")
    public Storehouse addProductToStorehouse(@PathVariable final Long storehouseId, @PathVariable final Long productId) {
        Storehouse storehouse = service.addProductToStorehouse(productId, storehouseId);
        return storehouse;
    }

    @DeleteMapping("/storehouses/{storehouseId}/products/{productId}/remove")
    public Storehouse removeProductFromStorehouse(@PathVariable final Long storehouseId, @PathVariable final Long productId) {
        Storehouse storehouse = service.removeProductFromStorehouse(productId, storehouseId);
        return storehouse;
    }

    @PutMapping("/storehouses/{oldStorehouseId}/moveTo/{newStorehouseId}")
    public Storehouse moveProductsBetweenCategories(@PathVariable final Long oldStorehouseId, @PathVariable final Long newStorehouseId) {
        Storehouse storehouse = service.moveProductsBetweenStorehouses(oldStorehouseId, newStorehouseId);
        return storehouse;
    }
}

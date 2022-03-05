package com.example.simplestoragesystem.service;

import com.example.simplestoragesystem.exception.ProductHasAlreadyStorehouseException;
import com.example.simplestoragesystem.exception.StorehouseNotFoundException;
import com.example.simplestoragesystem.model.Product;
import com.example.simplestoragesystem.model.Storehouse;
import com.example.simplestoragesystem.repository.StorehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class StorehouseService {
    private final StorehouseRepository repository;
    private final ProductService productService;

    @Autowired
    public StorehouseService(StorehouseRepository repository, ProductService productService) {
        this.repository = repository;
        this.productService = productService;
    }

    public Storehouse createStorehouse(Storehouse storehouse) {
        return repository.save(storehouse);
    }

    public List<Storehouse> createStorehousesBulk(List<Storehouse> storehouses) {
        return StreamSupport.stream(repository.saveAll(storehouses).spliterator(), false).collect((Collectors.toList()));
    }

    public List<Storehouse> readStorehouses() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Storehouse readStorehouse(Long id) {
        return repository.findById(id).orElseThrow(() -> new StorehouseNotFoundException(id));
    }

    @Transactional
    public Storehouse updateStorehouse(Long id, Storehouse storehouse) {
        Storehouse storehouseToEdit = readStorehouse(id);
        storehouseToEdit.setName(storehouse.getName());
        storehouseToEdit.setProducts(storehouse.getProducts());
        storehouseToEdit.setAddress(storehouse.getAddress());
        return  storehouseToEdit;
    }

    public Storehouse deleteStorehouse(Long id) {
        Storehouse storehouse = readStorehouse(id);
        repository.delete(storehouse);
        return storehouse;
    }

    @Transactional
    public Storehouse addProductToStorehouse(Long productId, Long storehouseId) {
        Storehouse storehouse = readStorehouse(storehouseId);
        Product product = productService.readProduct(productId);
        if(Objects.nonNull(product.getStorehouse())) {
            throw new ProductHasAlreadyStorehouseException(productId, storehouseId);
        }
        storehouse.addProduct(product);
        return  storehouse;
    }

    @Transactional
    public Storehouse removeProductFromStorehouse(Long productId, Long storehouseId) {
        Storehouse storehouse = readStorehouse(storehouseId);
        Product product = productService.readProduct(productId);
        storehouse.removeProduct(product);
        return storehouse;
    }

    public Storehouse moveProductsBetweenStorehouses(Long oldStorehouseId, Long newStorehouseId) {
        Storehouse oldStorehouse = readStorehouse(oldStorehouseId);
        Storehouse newStorehouse = readStorehouse(newStorehouseId);
        List<Product> productsToMove = oldStorehouse.getProducts();
        newStorehouse.addProductsBulk(productsToMove);
        return newStorehouse;
    }

    public boolean checkProductsList(Long storehouseId){
        Storehouse storehouse = readStorehouse(storehouseId);
        return storehouse.checkProductsListEmpty();
    }
}

package com.example.simplestoragesystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatbase {
    private static final Logger log = LoggerFactory.getLogger(LoadDatbase.class);

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository, OrderRepository orderRepository, ProducerRepository producerRepository, CategoryRepository categoryRepository, StorehouseRepository storehouseRepository){
        return args -> {
            log.info("Preloading product " + productRepository.save(new Product("Keyboard", 12, 199.99f)));
            log.info("Preloading product " + productRepository.save(new Product("Mouse", 49, 59.99f)));

            productRepository.findAll().forEach(product -> log.info("Preloaded products: " + product));

            log.info("Preloading order " + orderRepository.save(new Order("Keyboard", Status.COMPLETED)));
            log.info("Preloading order " + orderRepository.save(new Order("Mouse", Status.IN_PROGRESS)));

            orderRepository.findAll().forEach(order -> log.info("Preloaded orders: " + order));

            log.info("Preloading producer " + producerRepository.save(new Producer("Asus")));
            log.info("Preloading producer " + producerRepository.save(new Producer("Intel")));

            producerRepository.findAll().forEach(producer -> log.info("Preloaded producers: " + producer));

            log.info("Preloading category " + categoryRepository.save(new Category("Computer devices")));
            log.info("Preloading category " + categoryRepository.save(new Category("Laptops")));

            categoryRepository.findAll().forEach(category -> log.info("Preloaded categories: " + category));

            log.info("Preloading storehous " + storehouseRepository.save(new Storehouse("Central Masuria Storehouse", "Olecko ul. Mickiewicza 33")));
            log.info("Preloading storehous " + storehouseRepository.save(new Storehouse("Regional Suwalki Storehouse", "Suwalki ul. Konopnickiej 113")));

            storehouseRepository.findAll().forEach(storehouse -> log.info("Preloaded storehouses: " + storehouse));
        };
    }
}

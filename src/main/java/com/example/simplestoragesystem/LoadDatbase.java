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
    CommandLineRunner initDatabase(ProductRepository productRepository, OrderRepository orderRepository){
        return args -> {
            log.info("Preloading " + productRepository.save(new Product("Keyboard", 12, 199.99f)));
            log.info("Preloading " + productRepository.save(new Product("Mouse", 49, 59.99f)));

            productRepository.findAll().forEach(product -> log.info("Preloaded " + product));

            log.info("Preloading " + orderRepository.save(new Order("Keyboard", Status.COMPLETED)));
            log.info("Preloading " + orderRepository.save(new Order("Mouse", Status.IN_PROGRESS)));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });
        };
    }
}

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
    CommandLineRunner initDatabase(ProductRepository repository){
        return args -> {
            log.info("Preloading " + repository.save(new Product("Keyboard", 12, 199.99f)));
            log.info("Preloading " + repository.save(new Product("Mouse", 49, 59.99f)));
        };
    }
}

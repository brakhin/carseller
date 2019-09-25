package ru.bgbrakhi.carseller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.bgbrakhi.carseller.service.StorageServiceImpl;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(StorageServiceImpl storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}

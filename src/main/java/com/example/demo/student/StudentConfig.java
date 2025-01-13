package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner( StudentRepository repository) {
        return args -> {
            Student mariam = new Student(
                    "mariam",
                    "mariam.jamal@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );
            Student james = new Student(
                    "James",
                    "james@gmail.com",
                    LocalDate.of(2004, Month.JUNE, 22)
            );

            repository.saveAll(
                    List.of(mariam, james)
            );

        };
    }
}

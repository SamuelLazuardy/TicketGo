package com.patterndesign.ticketGo;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.patterndesign.ticketGo.Domain.TicketEntity;
import com.patterndesign.ticketGo.repositiories.TicketRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(TicketRepository ticketRepository) {
        return args -> {
            if (ticketRepository.count() == 0) {
                ticketRepository.saveAll(Arrays.asList(
                    TicketEntity.builder().id("1").name("John").type("Concert").price(150).build(),
                    TicketEntity.builder().id("2").name("Doe").type("Concert").price(50).build(),
                    TicketEntity.builder().id("3").name("Sam").type("Concert").price(20).build()
                ));
            }
        };
    }
}
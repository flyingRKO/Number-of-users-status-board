package com.rko.numberofusersstatusboard.config;

import com.rko.numberofusersstatusboard.repository.EventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public EventRepository eventRepository() {
        return new EventRepository() {};
    }
}

package com.max.education.mongo.config;

import com.max.education.mongo.entity.PersonMongo;
import com.max.education.mongo.repository.PersonRepoMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Max
 */
@Configuration
@RequiredArgsConstructor
public class InitializeMongoConfig {
    private final PersonRepoMongo personRepoMongo;

    @Bean
    public PersonMongo createAndSavePersonMongo() {
        PersonMongo personMongo = new PersonMongo(77, "RAND_OM", "rand@mail.ru");
        personRepoMongo.save(personMongo);
        return personMongo;
    }
}
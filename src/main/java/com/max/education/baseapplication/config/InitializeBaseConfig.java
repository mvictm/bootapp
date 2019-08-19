package com.max.education.baseapplication.config;

import com.max.education.baseapplication.entity.PersonH2;
import com.max.education.baseapplication.repository.PersonRepoH2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This bean initialize, while application start. After run, database (H2) will contains
 * one record.
 *
 * @author Max
 * */
@Configuration
@RequiredArgsConstructor
public class InitializeBaseConfig {

    private final PersonRepoH2 personRepoH2;

    @Bean
    public PersonH2 createAndSavePersonH2() {
        PersonH2 personH2 = new PersonH2("Random", "randommail@mail.ru");
        personRepoH2.save(personH2);
        return personH2;
    }
}
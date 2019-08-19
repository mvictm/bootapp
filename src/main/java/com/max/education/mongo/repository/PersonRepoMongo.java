package com.max.education.mongo.repository;

import com.max.education.mongo.entity.PersonMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepoMongo extends MongoRepository<PersonMongo, Integer> {
}

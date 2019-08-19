package com.max.education.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Simple entity for Mongo database.
 *
 * @author Max
 */

@Document(collection = "demo")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class PersonMongo {
    @Id
    public int id;
    public String name;
    public String email;
}

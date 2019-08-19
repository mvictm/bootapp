package com.max.education.baseapplication.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;

/**
 * Simple entity, which mapping to database and Java object
 *
 * @author Max
 */

@Getter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name = "person")
@ToString
public class PersonH2 {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Setter
    @NonNull
    private String name;
    @Setter
    @Email
    @NonNull
    private String email;
}

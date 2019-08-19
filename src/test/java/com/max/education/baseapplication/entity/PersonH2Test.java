package com.max.education.baseapplication.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("╯°□°）╯ PersonTest")
class PersonH2Test {
    private String name = "Joe";
    private String email = "joe@mail.ru";

    @Test
    public void checkGetter() {
        PersonH2 personH2 = new PersonH2("Joe", "joe@mail.ru");

        assertEquals(personH2.getName(), name);
        assertEquals(personH2.getEmail(), email);
    }

    @Test
    public void checkSetter() {
        PersonH2 personH2 = new PersonH2();
        personH2.setName("Joe");
        personH2.setEmail("joe@mail.ru");

        assertEquals(personH2.getName(), name);
        assertEquals(personH2.getEmail(), email);
    }

    @Test
    public void checkToString() {
        PersonH2 personH2 = new PersonH2(name, email);
        assertEquals(personH2.toString(), "PersonH2(id=0, name=Joe, email=joe@mail.ru)");
    }
}
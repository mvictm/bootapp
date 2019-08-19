package com.max.education.baseapplication.controller;

import com.max.education.baseapplication.entity.PersonH2;
import com.max.education.baseapplication.repository.PersonRepoH2;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Controller \uD83D\uDC4D")
class PersonH2ControllerTest {
    private static PersonControllerH2 personControllerH2;
    private static Model model;
    private static BindingResult bindingResult;

    @BeforeAll
    static void initialize() {
        model = Mockito.mock(Model.class);
        bindingResult = Mockito.mock(BindingResult.class);
        personControllerH2 = new PersonControllerH2(Mockito.mock(PersonRepoH2.class));
    }

    @Test
    public void checkController() {
        PersonH2 personH2 = new PersonH2("Joe", "joe@,ail.ru");

        assertThat(personControllerH2.viewUsers(model)).isEqualTo("users");
        assertThat(personControllerH2.addPerson(personH2.getName(), personH2.getEmail(), model)).isEqualTo("users");
    }

    @Test
    public void deleteNonExistentPerson() {
        assertThrows(IllegalArgumentException.class,
                () -> personControllerH2.deletePerson(12, model));
    }

    @Test
    public void updateInvalidPerson() {
        PersonH2 invalidPersonH2 = new PersonH2("Joe", "joe@mail.ru");

        Mockito.when(bindingResult.hasErrors()).thenReturn(true);

        assertThat(personControllerH2.update(1, invalidPersonH2, bindingResult, model)).isEqualTo("edit");
    }
}
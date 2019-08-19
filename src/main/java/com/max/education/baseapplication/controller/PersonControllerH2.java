package com.max.education.baseapplication.controller;

import com.max.education.baseapplication.entity.PersonH2;
import com.max.education.baseapplication.repository.PersonRepoH2;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

/**
 * Simple controller of person. Can execute CRUD operations with person's list
 *
 * @author Max
 */
@Controller
@RequiredArgsConstructor
public class PersonControllerH2 {
    private final PersonRepoH2 personRepoH2;

    @GetMapping("/")
    public String viewUsers(Model model) {
        model.addAttribute("persons", personRepoH2.findAll());
        return "users";
    }

    @PostMapping("/add")
    public String addPerson(@RequestParam @NonNull String name,
                            @RequestParam @NonNull String email,
                            Model model) {
        if (!name.isEmpty() && !email.isEmpty()) {
            PersonH2 newPersonH2 = new PersonH2(name, email);
            personRepoH2.save(newPersonH2);
        } else {
            model.addAttribute("error", "You try to add empty person!");
        }
        model.addAttribute("persons", personRepoH2.findAll());
        return "users";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") int id, Model model) {
        PersonH2 personH2 = personRepoH2.findById(id).orElseThrow(IllegalArgumentException::new);
        personRepoH2.delete(personH2);
        model.addAttribute("persons", personRepoH2.findAll());
        return "users";
    }

    @GetMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model) {
        PersonH2 personH2 = personRepoH2.findById(id).orElseThrow(IllegalArgumentException::new);
        model.addAttribute("personH2", personH2);
        return "edit";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") int id, @Valid PersonH2 newPersonH2, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edit";
        }

        PersonH2 personH2 = personRepoH2.findById(id).orElseThrow(IllegalArgumentException::new);
        personH2.setName(newPersonH2.getName());
        personH2.setEmail(newPersonH2.getEmail());
        model.addAttribute("persons", personRepoH2.findAll());
        return "users";
    }
}
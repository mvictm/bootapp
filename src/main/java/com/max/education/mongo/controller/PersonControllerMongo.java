package com.max.education.mongo.controller;

import com.max.education.mongo.repository.PersonRepoMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Simple controller. Used Mongo database.
 *
 * @author Max
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/mongo")
public class PersonControllerMongo {
    private final PersonRepoMongo personRepoMongo;

    @GetMapping("/get")
    public String viewUsers(Model model) {
        model.addAttribute("persons", personRepoMongo.findAll());
        return "userMongo";
    }
}
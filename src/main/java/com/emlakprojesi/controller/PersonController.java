package com.emlakprojesi.controller;

import javax.validation.Valid;

import com.emlakprojesi.domain.Person;
import com.emlakprojesi.domain.PersonRole;
import com.emlakprojesi.service.RealEstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/people")
public class PersonController {

    private final RealEstateService realEstateService;

    public PersonController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping
    public String list(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("people", realEstateService.listPeople(keyword));
        model.addAttribute("person", new Person());
        model.addAttribute("roles", PersonRole.values());
        model.addAttribute("keyword", keyword);
        return "people/list";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Person person, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("people", realEstateService.listPeople(null));
            model.addAttribute("roles", PersonRole.values());
            return "people/list";
        }
        realEstateService.savePerson(person);
        return "redirect:/people";
    }
}

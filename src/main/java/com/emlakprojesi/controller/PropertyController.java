package com.emlakprojesi.controller;

import javax.validation.Valid;

import com.emlakprojesi.domain.Property;
import com.emlakprojesi.domain.PropertyStatus;
import com.emlakprojesi.domain.PropertyType;
import com.emlakprojesi.service.RealEstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final RealEstateService realEstateService;

    public PropertyController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping("/new")
    public String form(Model model) {
        prepareForm(model, new Property());
        return "properties/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Property property, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, property);
            return "properties/form";
        }
        realEstateService.saveProperty(property);
        return "redirect:/";
    }

    private void prepareForm(Model model, Property property) {
        model.addAttribute("property", property);
        model.addAttribute("types", PropertyType.values());
        model.addAttribute("statuses", PropertyStatus.values());
        model.addAttribute("people", realEstateService.allPeople());
    }
}

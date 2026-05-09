package com.emlakprojesi.controller;

import com.emlakprojesi.domain.PropertyStatus;
import com.emlakprojesi.service.RealEstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    private final RealEstateService realEstateService;

    public HomeController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping("/")
    public String index(@RequestParam(required = false) String keyword,
                        @RequestParam(required = false) PropertyStatus status,
                        Model model) {
        model.addAttribute("summary", realEstateService.dashboardSummary());
        model.addAttribute("properties", realEstateService.searchProperties(keyword, status));
        model.addAttribute("statuses", PropertyStatus.values());
        model.addAttribute("selectedStatus", status);
        model.addAttribute("keyword", keyword);
        return "index";
    }
}

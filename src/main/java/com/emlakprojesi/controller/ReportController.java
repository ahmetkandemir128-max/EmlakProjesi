package com.emlakprojesi.controller;

import com.emlakprojesi.service.RealEstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReportController {

    private final RealEstateService realEstateService;

    public ReportController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping("/reports")
    public String reports(Model model) {
        model.addAttribute("summary", realEstateService.dashboardSummary());
        model.addAttribute("deals", realEstateService.allDeals());
        return "reports/index";
    }
}

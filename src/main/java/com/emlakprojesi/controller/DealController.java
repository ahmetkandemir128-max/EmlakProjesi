package com.emlakprojesi.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import com.emlakprojesi.domain.Deal;
import com.emlakprojesi.domain.DealType;
import com.emlakprojesi.domain.PropertyStatus;
import com.emlakprojesi.service.RealEstateService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/deals")
public class DealController {

    private final RealEstateService realEstateService;

    public DealController(RealEstateService realEstateService) {
        this.realEstateService = realEstateService;
    }

    @GetMapping
    public String list(Model model) {
        prepareModel(model, newDeal());
        return "deals/list";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute Deal deal, BindingResult bindingResult, Model model) {
        validateDealStatus(deal, bindingResult);
        if (bindingResult.hasErrors()) {
            prepareModel(model, deal);
            return "deals/list";
        }
        realEstateService.saveDeal(deal);
        return "redirect:/deals";
    }

    private void prepareModel(Model model, Deal deal) {
        model.addAttribute("deal", deal);
        model.addAttribute("deals", realEstateService.allDeals());
        model.addAttribute("dealTypes", DealType.values());
        model.addAttribute("properties", realEstateService.activeProperties());
        model.addAttribute("people", realEstateService.allPeople());
    }

    private Deal newDeal() {
        Deal deal = new Deal();
        deal.setDealDate(LocalDate.now());
        return deal;
    }

    private void validateDealStatus(Deal deal, BindingResult bindingResult) {
        if (deal.getProperty() == null || deal.getType() == null) {
            return;
        }
        if (DealType.SALE.equals(deal.getType()) && !PropertyStatus.FOR_SALE.equals(deal.getProperty().getStatus())) {
            bindingResult.rejectValue("property", "deal.property.saleOnly", "Satis icin satilik bir emlak seciniz.");
        }
        if (DealType.RENTAL.equals(deal.getType()) && !PropertyStatus.FOR_RENT.equals(deal.getProperty().getStatus())) {
            bindingResult.rejectValue("property", "deal.property.rentOnly", "Kiralama icin kiralik bir emlak seciniz.");
        }
    }
}

package com.emlakprojesi.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import com.emlakprojesi.domain.Deal;
import com.emlakprojesi.domain.DealType;
import com.emlakprojesi.domain.Person;
import com.emlakprojesi.domain.Property;
import com.emlakprojesi.domain.PropertyStatus;
import com.emlakprojesi.repository.DealRepository;
import com.emlakprojesi.repository.PersonRepository;
import com.emlakprojesi.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RealEstateService {

    private final PersonRepository personRepository;
    private final PropertyRepository propertyRepository;
    private final DealRepository dealRepository;

    public RealEstateService(PersonRepository personRepository, PropertyRepository propertyRepository,
                             DealRepository dealRepository) {
        this.personRepository = personRepository;
        this.propertyRepository = propertyRepository;
        this.dealRepository = dealRepository;
    }

    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    public Property saveProperty(Property property) {
        return propertyRepository.save(property);
    }

    public Deal saveDeal(Deal deal) {
        Property property = deal.getProperty();
        if (DealType.SALE.equals(deal.getType())) {
            property.setStatus(PropertyStatus.SOLD);
        } else if (DealType.RENTAL.equals(deal.getType())) {
            property.setStatus(PropertyStatus.RENTED);
        }
        propertyRepository.save(property);
        return dealRepository.save(deal);
    }

    @Transactional(readOnly = true)
    public List<Person> listPeople(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return personRepository.findAll();
        }
        String normalized = keyword.trim();
        return personRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrNationalIdContaining(
                normalized, normalized, normalized);
    }

    @Transactional(readOnly = true)
    public List<Property> searchProperties(String keyword, PropertyStatus status) {
        return propertyRepository.search(keyword == null ? "" : keyword.trim(), status);
    }

    @Transactional(readOnly = true)
    public List<Person> allPeople() {
        return personRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Property> activeProperties() {
        return propertyRepository.findByStatusIn(Arrays.asList(PropertyStatus.FOR_SALE, PropertyStatus.FOR_RENT));
    }

    @Transactional(readOnly = true)
    public List<Deal> allDeals() {
        return dealRepository.findAllWithDetails();
    }

    @Transactional(readOnly = true)
    public DashboardSummary dashboardSummary() {
        return new DashboardSummary(
                propertyRepository.count(),
                personRepository.count(),
                propertyRepository.countByStatus(PropertyStatus.FOR_SALE),
                propertyRepository.countByStatus(PropertyStatus.FOR_RENT),
                propertyRepository.countByStatus(PropertyStatus.SOLD),
                propertyRepository.countByStatus(PropertyStatus.RENTED),
                dealRepository.count(),
                safeAmount(dealRepository.sumAmountByType(DealType.SALE)),
                safeAmount(dealRepository.sumAmountByType(DealType.RENTAL)));
    }

    private BigDecimal safeAmount(BigDecimal amount) {
        return amount == null ? BigDecimal.ZERO : amount;
    }
}

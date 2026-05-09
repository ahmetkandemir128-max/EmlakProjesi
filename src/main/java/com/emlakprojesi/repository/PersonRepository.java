package com.emlakprojesi.repository;

import java.util.List;

import com.emlakprojesi.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrNationalIdContaining(
            String firstName, String lastName, String nationalId);
}

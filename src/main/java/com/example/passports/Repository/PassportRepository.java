package com.example.passports.Repository;

import com.example.passports.Entity.Passport;
import com.example.passports.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassportRepository extends JpaRepository<Passport, Long> {


    //JPA запрос на	Получение данных о владельце паспорта по его серии и номеру
    Passport getByPersonalNumberAndDocumentNumber(String personalNumber, String documentNumber);

    boolean existsByPersonalNumberAndDocumentNumber(String personalNumber, String documentNumber);

    List<Passport> findAllByPeople(People people);
    List<Passport> findAllByPeople_Id(Long id);
    void deleteAllByPeople(People people);


    List<Passport> findAllByStatus(String status);

}

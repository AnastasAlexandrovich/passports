package com.example.passports.cucumber.test.bdd.glue;

import com.example.passports.Entity.Passport;
import com.example.passports.Entity.PassportDTO;
import com.example.passports.Entity.People;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeoplePassportSteps {
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    // ожидаем
    private List<People> expectedPeoples;

    // ожидаем
    private List<Passport> expectedPassports;
    // по факту получаем
    private List<Passport> actualPassports;



    @Before
    public void setup(){
        expectedPeoples = new ArrayList<>();

        expectedPassports = new ArrayList<>();
        actualPassports = new ArrayList<>();

        passportRepository.deleteAll();
        peopleRepository.deleteAll();

    }

    @Given("^the person$")
    public void givenTheFollowingUser(final List<People> peoples){
        // подготовить репозитории, добавить челика
        expectedPeoples.addAll(peoples);
        peopleRepository.saveAll(peoples);
        System.out.println("pass");
    }

    @And("^following passports$")
    public void givenTheFollowingPassoprts(final List<PassportDTO> passports){
        // подготовить репозитории, добавить паспорт его

        Passport p = new Passport();
        for (PassportDTO p1: passports) {
            p.setId(p1.getId());
            p.setPeople(peopleRepository.getById(p1.getPeople()));
            p.setTypeOfPassport(p1.getTypeOfPassport());
            p.setStatus(p1.getStatus());
            p.setAuthority(p1.getAuthority());
            p.setDateOfExpiry(p1.getDateOfExpiry());
            p.setDateOfIssue(p1.getDateOfIssue());
            p.setDocumentNumber(p1.getDocumentNumber());
            p.setPersonalNumber(p1.getPersonalNumber());

            expectedPassports.add(p);

        }
        passportRepository.saveAll(expectedPassports);
    }

    @When("^user gets passports$")
    public void whenUserGoesByEndpoint() throws Exception {

        actualPassports.addAll(Arrays.asList(objectMapper.readValue(
                testRestTemplate.getForEntity("/passport/get-all-by-arguments?surname=Хорошилова&name=Анастасия&dateOfBirth=01.01.2000",
                        String.class).getBody(), Passport[].class)));
    }

    @Then("^get result passports$")
    public void getResultOfThePerson(){

        validatePassport();
        System.out.println("Test 3");
    }

    private void validatePassport(){
        Assertions.assertEquals(expectedPassports.size(), actualPassports.size());

        for (int i=0; i<expectedPassports.size(); i++) {

            Assertions.assertEquals(expectedPassports.get(i).getPeople().getId(), actualPassports.get(i).getPeople().getId());

        }
    }
}

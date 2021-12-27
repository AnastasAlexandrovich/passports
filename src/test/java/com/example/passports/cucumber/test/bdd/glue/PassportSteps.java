package com.example.passports.cucumber.test.bdd.glue;

import com.example.passports.Entity.Passport;
import com.example.passports.Entity.People;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PassportSteps {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private PassportRepository passportRepository;
    @Autowired
    private PeopleRepository peopleRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    // ожидаем
    private List<People> expectedPeoples;
    // по факту получаем
    private List<People> actualPeoples;

    @DefaultDataTableCellTransformer
    @DefaultDataTableEntryTransformer
    @DefaultParameterTransformer
    public Object transform(final Object from, final Type to){
        return objectMapper.convertValue(from, objectMapper.constructType(to));
    }



    @Before
    public void setup(){
        expectedPeoples = new ArrayList<>();
        actualPeoples = new ArrayList<>();
        passportRepository.deleteAll();
        peopleRepository.deleteAll();

    }

    @Given("^the following person$")
    public void givenTheFollowingUser(final List<People> peoples){
        // подготовить репозитории, добавить челика и паспорт его
        expectedPeoples.addAll(peoples);
        peopleRepository.saveAll(peoples);

        //добавим нужный паспорт?
        Passport passport1 = new Passport();
        passport1.setId(16L);
        passport1.setPeople(peoples.get(0));
        passport1.setPersonalNumber("0000");
        passport1.setDocumentNumber("111111");
        passport1.setAuthority("ГУ МВД РОССИИ");
        passport1.setDateOfIssue("12.03.2013");
        passport1.setDateOfExpiry("12.03.2023");
        passport1.setTypeOfPassport("ID");
        passport1.setStatus("ACTIVE");

        passportRepository.save(passport1);

    }

    @When("^user goes by endpoint$")
    public void whenUserGoesByEndpoint() throws Exception {

        actualPeoples.add(objectMapper.readValue(
                testRestTemplate.getForEntity("/passport/find-people-by-personal-number-and-document-number?serialNumber=0000&passportNumber=111111",
                        String.class).getBody(), People.class));
    }

    @Then("^get result of the person$")
    public void getResultOfThePerson(){

        validatePassport();

    }

    private void validatePassport(){
        Assertions.assertEquals(expectedPeoples.size(), actualPeoples.size());

        People expectedPerson = expectedPeoples.get(0);
        People actualPerson = actualPeoples.get(0);

        Assertions.assertEquals(expectedPerson.getId(), actualPerson.getId());
    }

}

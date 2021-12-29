package com.example.passports.cucumber.test.bdd.glue;

import com.example.passports.Entity.People;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PeopleSteps {
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
    // по факту получаем
    private List<People> actualPeoples;


    @Before
    public void setup(){
        expectedPeoples = new ArrayList<>();
        actualPeoples = new ArrayList<>();
        peopleRepository.deleteAll();

    }

    @Given("^the following peoples$")
    public void givenTheFollowingUser(final List<People> peoples){
        // подготовить репозитории, добавить челика и паспорт его
        expectedPeoples.addAll(peoples);
        peopleRepository.saveAll(peoples);
    }

    @When("^user goes by special endpoint$")
    public void whenUserGoesByEndpoint() throws Exception {

        actualPeoples.addAll(Arrays.asList(objectMapper.readValue(
                testRestTemplate.getForEntity("/people/peoples",
                        String.class).getBody(), People[].class)));
    }

    @Then("^get list of peoples$")
    public void getResultOfThePerson(){

        validatePassport();
        System.out.println("Test 2");

    }

    private void validatePassport(){
        Assertions.assertEquals(expectedPeoples.size(), actualPeoples.size());

        for (int i=0; i<expectedPeoples.size(); i++) {

            Assertions.assertTrue(expectedPeoples.contains(actualPeoples.get(i)));
            expectedPeoples.remove(actualPeoples.get(i));

        }
    }

}

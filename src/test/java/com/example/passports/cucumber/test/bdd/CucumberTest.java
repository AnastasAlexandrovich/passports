package com.example.passports.cucumber.test.bdd;


import com.example.passports.PassportsApplication;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;


@RunWith(Cucumber.class)
@CucumberContextConfiguration()
@SpringBootTest(classes = {PassportsApplication.class, CucumberTest.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberOptions(features = "classpath:features", plugin = {"pretty"})
public class CucumberTest {
}

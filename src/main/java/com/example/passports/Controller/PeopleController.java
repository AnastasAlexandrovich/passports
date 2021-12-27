package com.example.passports.Controller;

import com.example.passports.Entity.People;
import com.example.passports.Service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @DeleteMapping("/delete/{peopleId}")
    public ResponseEntity<String> deletePassportsByPeople(@PathVariable Long peopleId) throws Exception {
        return new ResponseEntity<>(peopleService.deletePassportByPeople(peopleId), HttpStatus.OK);
    }

    @GetMapping("/peoples")
    public List<People> getPeoples(){
        return peopleService.getPeoples();
    }
}


package com.example.passports.Service;

import com.example.passports.Entity.People;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PeopleService {

    @Autowired
    private PassportRepository passportRepository;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PassportService passportService;


    @Transactional
    public String deletePassportByPeople(Long id) throws Exception {

        if (!peopleRepository.existsById(id)) {
            return "человек с id = " + id + " не существует!";
        }
        // passportService.deleteByPeople(peopleRepository.getById(id));
        peopleRepository.deleteById(id);
        return "Все паспорты текущего человека удалены!";
    }

    public List<People> getPeoples()
    {
        return peopleRepository.findAll();
    }
}

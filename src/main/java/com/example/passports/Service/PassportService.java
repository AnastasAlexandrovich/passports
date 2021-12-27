package com.example.passports.Service;

import com.example.passports.DTO.PassportListFindDTO;
import com.example.passports.Entity.Passport;
import com.example.passports.Entity.People;
import com.example.passports.Entity.ViewActivePassports;
import com.example.passports.Repository.ActivePassportsRepository;
import com.example.passports.Repository.PassportRepository;
import com.example.passports.Repository.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PassportService {

    //Вызов JPA репозитория PeopleRepository
    @Autowired
    private PassportRepository passportRepository;

    //Вызов JPA репозитория PeopleRepository
    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private ActivePassportsRepository activePassportsRepository;

    // 1)	Получение данных о владельце паспорта по его серии и номеру
    public ResponseEntity<?> findByPersonalNumberAndDocumentNumber(String serialNumber, String passportNumber) throws Exception {

        if (passportRepository.existsByPersonalNumberAndDocumentNumber(serialNumber, passportNumber)){
            Passport p = passportRepository.getByPersonalNumberAndDocumentNumber(serialNumber, passportNumber);
            return new ResponseEntity<People>(p.getPeople(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("Не найдено!", HttpStatus.OK);
    }

    //2)	Получение данных о всех паспортных данных по фамилии, имени, году рождения.
    // Любой из этих параметров может быть не задан. Если не задан ни один параметр – необходимо вывести все паспортные данные.
    public List<Passport> getAllByArguments(PassportListFindDTO passportListFindDTO) {
        if (passportListFindDTO.getSurname() == null &&
                passportListFindDTO.getName() == null &&
                passportListFindDTO.getDateOfBirth() == null) {
            return passportRepository.findAll();
        }

        List<People> peopleList = peopleRepository.findAllBySurnameOrNameOrDateOfBirth(passportListFindDTO.getSurname(),
                passportListFindDTO.getName(), passportListFindDTO.getDateOfBirth());

        List<Passport> passportList = new ArrayList<>();
        for (People p : peopleList) {
            List<Passport> passportListOnePeople = passportRepository.findAllByPeople(p);
            passportList.addAll(passportListOnePeople);
        }

        return passportList;
    }

    public List<ViewActivePassports> getAllViewActivePassports(){
        return activePassportsRepository.findAll();
    }

    public void deleteByPeople(People people){
        passportRepository.deleteAllByPeople(people);
    }


}

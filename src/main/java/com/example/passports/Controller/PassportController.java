package com.example.passports.Controller;

import com.example.passports.DTO.PassportListFindDTO;
import com.example.passports.Entity.Passport;
import com.example.passports.Entity.ViewActivePassports;
import com.example.passports.Service.PassportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//RestController чтобы делать запрос
//                                              это часть не меняется
@RestController//                                         V
//часть API который не менятся. Пример: localhost:8080/passport/....
@RequestMapping("/passport")
public class PassportController {

    @Autowired
    private PassportService passportService;

    //Get запрос на 1)	Получение данных о владельце паспорта по его серии и номеру
    @GetMapping("/find-people-by-personal-number-and-document-number")
    public ResponseEntity<?> findByPersonalNumberAndDocumentNumber(@RequestParam String serialNumber, @RequestParam String passportNumber) throws Exception {
        return passportService.findByPersonalNumberAndDocumentNumber(serialNumber, passportNumber);
    }

    //Get запрос на 2)	Получение данных о всех паспортных данных по фамилии, имени, году рождения.
    @GetMapping("/get-all-by-arguments")
    public List<Passport> getAllByArguments(@RequestBody PassportListFindDTO passportListFindDTO){
        return passportService.getAllByArguments(passportListFindDTO);
    }

    @GetMapping("/activate")
    public List<ViewActivePassports> getAllViewActivePassports(){
        return passportService.getAllViewActivePassports();
    }

}

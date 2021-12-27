package com.example.passports.Repository;

import com.example.passports.Entity.People;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PeopleRepository extends JpaRepository<People, Long> {

    //Jpa запрос на	получение данных о всех паспортных данных по фамилии, имени, году рождения.
    // Любой из этих параметров может быть не задан. Если не задан ни один параметр – необходимо вывести все паспортные данные
    List<People> findAllBySurnameOrNameOrDateOfBirth(String surname, String name, String dateOfBirth);
}

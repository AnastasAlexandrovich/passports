package com.example.passports.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

//DAO класс для - Получение данных о всех паспортных данных по фамилии, имени, году рождения
public class PassportListFindDTO {
    private String surname;
    private String name;
    private String dateOfBirth;
}

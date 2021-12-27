package com.example.passports.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data

//DAО класс для поиска - 1)	Получение данных о владельце паспорта по его серии и номеру
public class PeopleFindDTO {

    private String serialNumber;
    private String passportNumber;
}

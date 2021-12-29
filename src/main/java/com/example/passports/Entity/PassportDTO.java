package com.example.passports.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data

@Entity
@Table(name = "passports")
public class PassportDTO {

    @Id
    private Long id;

    @Column(name = "people")
    private Long people;

    @Column(name = "personal_number")
    private String personalNumber;

    @Column(name = "document_number")
    private String documentNumber;

    @Column(name = "authority")
    private String authority;

    @Column(name = "date_of_issue")
    private String dateOfIssue;

    @Column(name = "date_of_expiry")
    private String dateOfExpiry;

    @Column(name = "type_of_passport")
    private String typeOfPassport;

    @Column(name = "status")
    private String status;
}

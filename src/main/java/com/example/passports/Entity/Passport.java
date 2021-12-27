package com.example.passports.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data



//сущность в базе данных - паспорт
@Entity
@Table(name = "passports")
public class Passport {
    @Id
    @SequenceGenerator(name = "passport_seq", sequenceName = "passport_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "passport_seq")
    private Long id;

    @ManyToOne
    private People people;

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


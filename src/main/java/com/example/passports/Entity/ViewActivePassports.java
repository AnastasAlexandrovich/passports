package com.example.passports.Entity;

import lombok.Data;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Immutable
@Subselect("select * from active_passports")
public class ViewActivePassports {
    @Id
    private Long id;

    private Long people_id;

    private String personal_number;

    private String document_number;

    private String authority;

    private String date_of_issue;

    private String date_of_expiry;

    private String type_of_passport;

    private String status;
}

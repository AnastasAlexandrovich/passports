package com.example.passports.Repository;

import com.example.passports.Entity.ViewActivePassports;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivePassportsRepository extends JpaRepository<ViewActivePassports, Long> {
}

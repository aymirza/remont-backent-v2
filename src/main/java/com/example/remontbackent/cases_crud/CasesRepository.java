package com.example.remontbackent.cases_crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CasesRepository extends JpaRepository<Cases, Long> {

    @Query("select c from Cases c where c.name=?1")
    List<Cases> getByName(String name);
}

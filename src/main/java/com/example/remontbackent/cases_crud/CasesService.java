package com.example.remontbackent.cases_crud;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CasesService {
    List<Cases> getAllCases();
    Cases saveCases(Cases cases);

    List<Cases> getByName(String name);
  List<Cases> getByName2(String name2);


}

package com.example.remontbackent.cases_crud;

import org.aspectj.weaver.patterns.ExactTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CasesServiceImpl implements CasesService {

    @Autowired
    private CasesRepository casesRepository;


    @Override
    public List<Cases> getAllCases() {
        return casesRepository.findAll();
    }

    @Override
    public Cases saveCases(Cases cases) {
        return casesRepository.save(cases);
    }

    @Override
    public List<Cases> getByName(String name) {
        try {
            return casesRepository.getByName(name);
        }catch (Exception e){
            return (List<Cases>) e;
        }
    }

    @Override
    public List<Cases> getByName2(String name2) {
        return casesRepository.getByName(name2);
    }


}

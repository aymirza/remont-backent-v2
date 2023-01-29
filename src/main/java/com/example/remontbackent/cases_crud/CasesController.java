package com.example.remontbackent.cases_crud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class CasesController {
    @Autowired
    private CasesService casesService;

    @GetMapping
    public  List<Cases>  getAllCases(){
        return casesService.getAllCases();
    }

    @PostMapping
    public Cases createCases(Cases cases){
        return casesService.saveCases(cases);
    }

    @GetMapping("/{name}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Cases>> getByName(@PathVariable(value = "name") String name){
        return new ResponseEntity<>(casesService.getByName(name), HttpStatus.OK);
    }

    @GetMapping("/2/{name2}")
    @PreAuthorize("hasRole('MODERATOR')")
    public List<Cases> getByName2(@PathVariable(value = "name2") String name2){
        return casesService.getByName2(name2);
    }



}

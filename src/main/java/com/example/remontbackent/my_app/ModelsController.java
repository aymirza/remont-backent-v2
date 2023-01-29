package com.example.remontbackent.my_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/models")
public class ModelsController {

    @Autowired
    private ModelsService modelsService;

    @GetMapping
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Models>> getAllModels(){
        return new ResponseEntity<>(modelsService.getAllModels(), HttpStatus.OK);
    }
    @PostMapping
    @PreAuthorize("hasRole('MODERATOR')")
    public Models createModels(Models models){
        return modelsService.saveModels(models);
    }

    @GetMapping("/{modelcode}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Models>> getByModelcode(@PathVariable(value = "modelcode") String modelcode){
        return  new ResponseEntity<>(modelsService.getByModelCode(modelcode), HttpStatus.OK);
    }

}

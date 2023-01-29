package com.example.remontbackent.my_app;

import com.example.remontbackent.cases_crud.Cases;
import com.example.remontbackent.cases_crud.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/repair")
public class RepairController {
    @Autowired
    private RepairService repairService;

    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepair(){
        return new ResponseEntity<>(repairService.getAllRepair(), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('MODERATOR')")
    public Repair createRepair(Repair repair){
        return repairService.saveRepair(repair);
    }

    @GetMapping("/{serinka}")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<List<Repair>> getBySerinka(@PathVariable(value = "serinka") String serinka){
        return  new ResponseEntity<>(repairService.getBySerinka(serinka), HttpStatus.OK);
    }


}

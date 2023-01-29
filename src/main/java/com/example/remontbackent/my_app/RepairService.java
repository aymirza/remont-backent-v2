package com.example.remontbackent.my_app;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface RepairService {
    List<Repair> getAllRepair();
    Repair saveRepair(Repair repair);

    List<Repair> getBySerinka(String serinka);


}

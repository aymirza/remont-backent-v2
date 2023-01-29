package com.example.remontbackent.my_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {

    @Autowired
    private RepairRepository repairRepository;


    @Override
    public List<Repair> getAllRepair() {
        return repairRepository.findAll();
    }

    @Override
    public Repair saveRepair(Repair repair) {
        return repairRepository.save(repair);
    }

    @Override
    public List<Repair> getBySerinka(String serinka) {
        try {
            return repairRepository.getBySerinka(serinka);
        }
        catch (Exception e){
            return (List<Repair>) e;
        }
    }
}

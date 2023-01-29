package com.example.remontbackent.my_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelsServiceImpl implements ModelsService{
    @Autowired
    private ModelsRepository modelsRepository;

    @Override
    public List<Models> getAllModels() {
        return modelsRepository.findAll();
    }

    @Override
    public Models saveModels(Models models) {
        return modelsRepository.save(models);
    }

    @Override
    public List<Models> getByModelCode(String modelcode) {
        try {
            return modelsRepository.getByModelcode(modelcode);
        }catch (Exception e){
            return (List<Models>) e;
        }
    }
}

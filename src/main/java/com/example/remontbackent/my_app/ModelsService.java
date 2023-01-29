package com.example.remontbackent.my_app;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ModelsService {
    List<Models> getAllModels();
    Models saveModels(Models models);

    List<Models> getByModelCode(String modelcode);
}

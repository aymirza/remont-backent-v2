package com.example.remontbackent.my_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelsRepository extends JpaRepository<Models, Long> {
    @Query("select m from Models m where m.modelcode=?1")
    List<Models> getByModelcode(String modelcode);
}

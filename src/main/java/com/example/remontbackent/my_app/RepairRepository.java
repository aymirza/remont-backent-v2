package com.example.remontbackent.my_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepairRepository extends JpaRepository<Repair, Long> {

    @Query("select r from Repair r where r.serinka=?1")
    List<Repair> getBySerinka(String serinka);
}

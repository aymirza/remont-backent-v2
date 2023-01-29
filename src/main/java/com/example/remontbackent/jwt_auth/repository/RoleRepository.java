package com.example.remontbackent.jwt_auth.repository;

import com.example.remontbackent.jwt_auth.models.ERole;
import com.example.remontbackent.jwt_auth.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

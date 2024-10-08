package com.example.college.repository;

import com.example.college.model.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {

    boolean existsByUsername(String username);

    AppUser findByUsername(String username);
}

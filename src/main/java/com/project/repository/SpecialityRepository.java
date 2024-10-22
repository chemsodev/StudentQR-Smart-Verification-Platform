package com.project.repository;

import com.project.model.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, Integer> {
    // Correct method name
    Optional<Speciality> findById(int id);
}

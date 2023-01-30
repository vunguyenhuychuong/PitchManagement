package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, String> {
}

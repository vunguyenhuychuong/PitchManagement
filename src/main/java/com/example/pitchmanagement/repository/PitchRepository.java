package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.Pitch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, String> {

    @Query("SELECT u FROM Pitch u WHERE CONCAT(u.pitchName, '', u.district, '', u.ward, '', u.pitchAddress)  LIKE %?1%")
    public Page<Pitch> findAll(String keyword, Pageable pageable);

    @Query("SELECT u FROM Pitch u Where u.estimation = 5")
    public List<Pitch> findAllByEstimation();
}

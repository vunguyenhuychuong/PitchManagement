package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.Child_Pitch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Child_PitchRepository extends JpaRepository<Child_Pitch, String> {
    @Query(value = "SELECT cp FROM Child_Pitch cp WHERE cp.pitchID.pitchID = ?1 AND cp.childrenPitchStatus = 1")
    public List<Child_Pitch> findAllChildPitchByPitchID(String pitchId);
}

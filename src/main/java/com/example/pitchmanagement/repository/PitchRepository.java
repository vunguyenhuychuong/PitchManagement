package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.Pitch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PitchRepository extends JpaRepository<Pitch, String> {

    @Query("SELECT u FROM Pitch u WHERE CONCAT(u.pitchName, '', u.district, '', u.ward, '', u.pitchAddress)  LIKE %?1%")
    public Page<Pitch> findAll(String keyword, Pageable pageable);

    @Query(value = "SELECT TOP(4) * FROM Pitch  WHERE estimation = 5 AND pitch_status = 1 order by NEWID()", nativeQuery = true)
    public List<Pitch> findAllByEstimation();

    public Pitch findPitchByPitchID(String s);

    @Query("SELECT p FROM Pitch p WHERE p.district.districtId = ?1 AND p.ward.wardId = ?2 AND p.status = 1")
    public Page<Pitch> filterPitch(String districtId, String wardId, Pageable pageable);
}

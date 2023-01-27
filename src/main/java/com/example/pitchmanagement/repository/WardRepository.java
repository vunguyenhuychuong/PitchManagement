package com.example.pitchmanagement.repository;

import com.example.pitchmanagement.entity.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, String> {

    @Query(value = "SELECT w FROM Ward w WHERE w.district.districtId = ?1")
    List<Ward> findWardListById(String districtId);
}

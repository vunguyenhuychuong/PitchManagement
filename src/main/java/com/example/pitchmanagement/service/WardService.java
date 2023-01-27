package com.example.pitchmanagement.service;

import com.example.pitchmanagement.entity.Ward;

import java.util.List;

public interface WardService {
    List<Ward> getWardByDistrictId(String districtId);

    Ward getWardById(String wardId);
}

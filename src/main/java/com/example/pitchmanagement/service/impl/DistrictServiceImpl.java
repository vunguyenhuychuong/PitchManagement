package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.District;
import com.example.pitchmanagement.repository.DistrictRepository;
import com.example.pitchmanagement.service.DistrictService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District getDistrictById (String districtId) {
        return districtRepository.findByDistrictId(districtId);
    }
}

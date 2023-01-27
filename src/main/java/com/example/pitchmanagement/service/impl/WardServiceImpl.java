package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.Ward;
import com.example.pitchmanagement.repository.WardRepository;
import com.example.pitchmanagement.service.WardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class WardServiceImpl implements WardService {

    private final WardRepository wardRepository;

    @Override
    public List<Ward> getWardByDistrictId(String districtId) {
        return wardRepository.findWardListById(districtId);
    }

    @Override
    public Ward getWardById(String wardId) {
        return wardRepository.findById(wardId).orElseThrow();
    }

}

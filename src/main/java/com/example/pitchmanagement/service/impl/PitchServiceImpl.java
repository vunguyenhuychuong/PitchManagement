package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.Pitch;
import com.example.pitchmanagement.repository.PitchRepository;
import com.example.pitchmanagement.service.PitchService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PitchServiceImpl implements PitchService {

    public static final int PITCHS_PER_PAGE = 12;
    private final PitchRepository repo;

    public PitchServiceImpl(PitchRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Pitch> listAll() {
        return repo.findAll();
    }

    @Override
    public Page<Pitch> listByPage(int pageNum, String keyword) {

        Pageable pageable = PageRequest.of(pageNum - 1, PITCHS_PER_PAGE);

        if (keyword != null) {
            return repo.findAll(keyword, pageable);
        }
        return repo.findAll(pageable);
    }

    @Override
    public List<Pitch> listAllByEstimation() {
        List<Pitch> listPitch = repo.findAllByEstimation();
        return listPitch;
    }

    @Override
    public Pitch getPitchByPitchID(String pitchID) {
        Pitch pitch = repo.findPitchByPitchID(pitchID);
        return pitch;
    }

    @Override
    public Page<Pitch> filteredPitch(String districtId, String wardId, int pageNum) {
        Pageable pageable = PageRequest.of(pageNum - 1, 1);
        return repo.filterPitch(districtId, wardId, pageable);
    }


}

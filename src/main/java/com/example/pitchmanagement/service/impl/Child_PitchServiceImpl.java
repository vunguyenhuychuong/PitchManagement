package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.Child_Pitch;
import com.example.pitchmanagement.repository.Child_PitchRepository;
import com.example.pitchmanagement.service.Child_PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Child_PitchServiceImpl implements Child_PitchService {
    @Autowired
    private Child_PitchRepository repo;

    @Override
    public List<Child_Pitch> listAllChildPitchByPitchID (String id) {
        List<Child_Pitch> listChildPitch = repo.findAllChildPitchByPitchID(id);
        return  listChildPitch;
    }
}

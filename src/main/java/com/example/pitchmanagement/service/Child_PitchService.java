package com.example.pitchmanagement.service;

import com.example.pitchmanagement.entity.Child_Pitch;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface Child_PitchService {
    List<Child_Pitch> listAllChildPitchByPitchID(String pitchID);
}

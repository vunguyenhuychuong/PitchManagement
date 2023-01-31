package com.example.pitchmanagement.service;

import com.example.pitchmanagement.entity.Pitch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PitchService {
    List<Pitch> listAll();

    Page<Pitch> listByPage(int pageNum, String keyword);
}

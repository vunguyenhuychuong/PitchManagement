package com.example.pitchmanagement.service.impl;

import com.example.pitchmanagement.entity.Pitch;
import com.example.pitchmanagement.repository.PitchRepository;
import com.example.pitchmanagement.service.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class PitchServiceImpl implements PitchService {

    @Autowired
    private PitchRepository repo;
    @Override
    public List<Pitch> getAll() {
        return repo.findAll();
    }

    @Override
    public Page<Pitch> paginatedPitch(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Pitch> listPitch = getAll();
        List<Pitch> listPaging;
        if(listPitch.size() < startItem){
            listPaging = Collections.emptyList();
        }else{
            int toIndex = Math.min(startItem + pageSize, listPitch.size());
            listPaging = listPitch.subList(startItem, toIndex);
        }
        Page<Pitch> pitchPage = new PageImpl<Pitch>(listPaging, PageRequest.of(currentPage, pageSize), listPitch.size());
        return pitchPage;
    }
}

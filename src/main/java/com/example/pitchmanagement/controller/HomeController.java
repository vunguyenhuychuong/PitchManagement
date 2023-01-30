package com.example.pitchmanagement.controller;

import com.example.pitchmanagement.entity.Pitch;
import com.example.pitchmanagement.service.PitchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class HomeController {

    @Autowired
    private PitchService service;
    @RequestMapping(value = {"/home", "/"})
    public String HomePage(Model model,
                           @RequestParam("page")Optional<Integer> page,
                           @RequestParam("size")Optional<Integer> size) {
        //-----------------------------------
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(8);
        Page<Pitch> pitchPage = service.paginatedPitch(PageRequest.of(currentPage-1, pageSize));
        model.addAttribute("pitchPage", pitchPage);
        int totalPages = pitchPage.getTotalPages();
        if(totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        //-----------------------------------
        return "home/index.html";
    }

}

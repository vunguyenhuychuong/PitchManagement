package com.example.pitchmanagement.controller;

import com.example.pitchmanagement.entity.Pitch;
import com.example.pitchmanagement.service.PitchService;
import com.example.pitchmanagement.service.impl.PitchServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PitchController {

    @Autowired
    private PitchService service;

//    @GetMapping("/home")
//    public String listAll(Model model)
//    {
//        List<Pitch> listPitchs = service.listAll();
//        model.addAttribute("listPitchs", listPitchs);
//        return "home/index";
//    }

    @GetMapping("/home")
    public String listFirstPage(Model model)
    {
        return listByPage(1, model, null);
    }

    @GetMapping("/home/page/{pageNum}")
    public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
                             @Param("keyword") String keyword) {
        Page<Pitch> page = service.listByPage(pageNum, keyword);
        List<Pitch> listPitchs = page.getContent();

        long startCount = (pageNum - 1) * PitchServiceImpl.PITCHS_PER_PAGE + 1;
        long endCount = startCount + PitchServiceImpl.PITCHS_PER_PAGE - 1;
        if(endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPitchs", listPitchs);
        model.addAttribute("keyword", keyword);

        return "home/index";
     }
}

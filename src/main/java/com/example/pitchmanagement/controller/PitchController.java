package com.example.pitchmanagement.controller;

import com.example.pitchmanagement.entity.Child_Pitch;
import com.example.pitchmanagement.entity.District;
import com.example.pitchmanagement.entity.Ward;
import com.example.pitchmanagement.entity.Pitch;
import com.example.pitchmanagement.service.Child_PitchService;
import com.example.pitchmanagement.service.DistrictService;
import com.example.pitchmanagement.service.PitchService;
import com.example.pitchmanagement.service.WardService;
import com.example.pitchmanagement.service.impl.PitchServiceImpl;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.apache.groovy.util.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class PitchController {

    private final DistrictService districtService;

    private final WardService wardService;

    private final PitchService service;

    private final Child_PitchService childPitchService;

    @GetMapping("/home")
    public String listFirstPage(Model model) {
        model.addAttribute("listDistricts", districtService.getAllDistricts());
        return listByPage(1, model, null);
    }

    @GetMapping("/home/page/{pageNum}")
    public String listByPage(@PathVariable(name = "pageNum") int pageNum, Model model,
                             @Param("keyword") String keyword) {
        Page<Pitch> page = service.listByPage(pageNum, keyword);
        List<Pitch> listPitchs = page.getContent();
        List<Pitch> listPitchByEstimation = service.listAllByEstimation();

        long startCount = (pageNum - 1) * PitchServiceImpl.PITCHS_PER_PAGE + 1;
        long endCount = startCount + PitchServiceImpl.PITCHS_PER_PAGE - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("listDistricts", districtService.getAllDistricts());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPitchs", listPitchs);
        model.addAttribute("keyword", keyword);
        model.addAttribute("listPitchEstimation", listPitchByEstimation);

        return "home/index.html";
    }

    @GetMapping(value = "/home/page/{pageNum}/filter")
    public String listByFilteredPage(Model model, final HttpServletRequest request,
                                     @PathVariable(name = "pageNum", required = false) int pageNum) {
        String districtID = request.getParameter("districtID");
        String wardID = request.getParameter("ward");

        Page<Pitch> page = service.filteredPitch(districtID, wardID, pageNum);
        List<Pitch> listPitchs = page.getContent();
        List<Pitch> listPitchByEstimation = service.listAllByEstimation();

        long startCount = (pageNum - 1) * PitchServiceImpl.PITCHS_PER_PAGE + 1;
        long endCount = startCount + PitchServiceImpl.PITCHS_PER_PAGE - 1;
        if (endCount > page.getTotalElements()) {
            endCount = page.getTotalElements();
        }

        model.addAttribute("listDistricts", districtService.getAllDistricts());
        model.addAttribute("totalPage", page.getTotalPages());
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("startCount", startCount);
        model.addAttribute("endCount", endCount);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPitchs", listPitchs);
        model.addAttribute("keyword", "");
        model.addAttribute("listPitchEstimation", listPitchByEstimation);
        model.addAttribute("listWards", wardService.getWardByDistrictId(districtID));
        model.addAttribute("district", districtID);
        model.addAttribute("ward", wardID);

        return "home/index.html";
    }

    @GetMapping (value = "pitch/viewPitchDetail/{pitchID}")
    public String viewPitchDetail(@PathVariable ("pitchID") String pitchID, Model model) {
        Pitch pitch = service.getPitchByPitchID(pitchID);
        District district = districtService.getDistrictById(pitch.getDistrict().getDistrictId());
        String pitchDistrict = district.getDistrictName();
        Ward ward = wardService.getWardById(pitch.getWard().getWardId());
        String pitchWard = ward.getWardName();
        List<Child_Pitch> listChildPitch = childPitchService.listAllChildPitchByPitchID(pitchID);
        model.addAttribute("pitch", pitch);
        model.addAttribute("listChildPitch", listChildPitch);
        model.addAttribute("pitchDistrict", pitchDistrict);
        model.addAttribute("pitchWard", pitchWard);
        return "pitch/pitchDetail.html";
    }

//    @GetMapping(value = "home/")
//    public String listByEstimation (Model model) {
//        List<Pitch> listPitchByEstimation = service.listAllByEstimation();
//        model.addAttribute("listPitchEstimation", listPitchByEstimation);
//        return "home/index.html";
//    }

}

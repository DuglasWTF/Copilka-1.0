package com.trusov.Copilka_1_0.controllers;

import com.trusov.Copilka_1_0.models.Period;
import com.trusov.Copilka_1_0.repo.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class PeriodController {
    @Autowired
    private PeriodRepository periodRepository;

    @GetMapping("/periods")
    public String editPeriodMain(Model model) {
        Iterable<Period> periods = periodRepository.findAll();
        model.addAttribute("periods", periods);
        return "periods-main";
    }

    @GetMapping("periods/add")
    public String createPeriod(Model model) {
        return "create-period";
    }

    @GetMapping("periods/edit/{id}")
    public String editPeriod(@PathVariable(value = "id") long id,
            Model model) {
        if (!periodRepository.existsById(id)){
            return "redirect:/periods";
        }
        Optional<Period> period =periodRepository.findById(id);
        ArrayList<Period>res = new ArrayList<>();
        period.ifPresent(res :: add);
        model.addAttribute("period", res);
        return "edit-period";
    }

    @PostMapping("/period/add")
    public String addPeriod(@RequestParam String name, @RequestParam String startDate, @RequestParam String endDate, Model model) {
        Period period = new Period(name, startDate, endDate);
        periodRepository.save(period);
        return "redirect:/periods";
    }

    @PostMapping("periods/edit/{id}")
    public String updatePeriod(
            @PathVariable(value = "id") long id,
            @RequestParam String name,
            @RequestParam String startDate,
            @RequestParam String endDate,
            Model model) {
        Period period = periodRepository.findById(id).orElseThrow();
        period.setStartDate(startDate);
        period.setEndDate(endDate);
        period.setName(name);
        periodRepository.save(period);
        return "redirect:/periods";
    }

    @PostMapping("periods/remove/{id}")
    public String deletePeriod(
        @PathVariable(value = "id") long id,
        Model model
    ){
        Period period =periodRepository.findById(id).orElseThrow();
        periodRepository.delete(period);
        return "redirect:/periods";
    }
}

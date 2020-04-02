package com.trusov.Copilka_1_0.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title", "Главная страница");
        return "home";
    }

}

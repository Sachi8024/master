package com.javatrain.conronovirustracker.controller;

import java.util.List;


import com.javatrain.conronovirustracker.models.LocationStats;
import com.javatrain.conronovirustracker.services.CoronovirusDataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    CoronovirusDataService coronovirusDataService;

    @GetMapping("/")
    public String home(Model model){

        List<LocationStats> allstats=coronovirusDataService.getAllstats();

        int totalReportedDeaths=allstats.stream().mapToInt(stat  -> stat.getTotalDeaths()).sum();
        model.addAttribute("locationstat", allstats);
        model.addAttribute("totalReportedDeaths", totalReportedDeaths);

        return "home";
    }
    
}

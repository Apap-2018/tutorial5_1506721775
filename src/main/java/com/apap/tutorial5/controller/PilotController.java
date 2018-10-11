package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PilotController {

    @Autowired
    private PilotService pilotService;

    @RequestMapping("/")
    private String home() {
        return "index";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.GET)
    private String add(Model model) {
        model.addAttribute("pilot", new PilotModel());
        return "addPilot";
    }

    @RequestMapping(value = "/pilot/add", method = RequestMethod.POST)
    private String addPilotSubmit(@ModelAttribute PilotModel pilot) {
        pilotService.addPilot(pilot);
        return "add";
    }

    @RequestMapping(value = "/pilot/view", method = RequestMethod.GET)
    private String viewPilot(@RequestParam(value = "licenseNumber") String licenseNumber, Model model) {
        model.addAttribute("pilotFlight", pilotService.getPilotDetailByLicenseNumber(licenseNumber).getPilotFlight());
        model.addAttribute("pilot", pilotService.getPilotDetailByLicenseNumber(licenseNumber));
        return "view-pilot";
    }

    @RequestMapping(value = "/pilot/delete/{id}")
    private String deletePilot(@PathVariable(value = "id") Long id) {
        pilotService.deletePilot(id);
        return "deleted";
    }

    @RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.GET)
    private String updatePilot(@PathVariable(value = "id") Long id, Model model) {

        model.addAttribute("pilot", pilotService.getPilotById(id));
        return "update-pilot";

    }

    @RequestMapping(value = "/pilot/update/{id}", method = RequestMethod.POST)
    private String updatePilot(@PathVariable(value = "id") long id, @ModelAttribute PilotModel pilot) {
        pilot.setId(id);
        pilotService.addPilot(pilot);
        return "updated";
    }
}

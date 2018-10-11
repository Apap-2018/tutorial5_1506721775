package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.service.FlightService;
import com.apap.tutorial5.service.PilotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private PilotService pilotService;

    @RequestMapping(value = "/flight/add/{licenseNumber}", method = RequestMethod.GET)
    private String add(@PathVariable(value = "licenseNumber") String licenseNumber, Model model) {
        ArrayList<FlightModel> listOfFlights = new ArrayList<>();
        listOfFlights.add(new FlightModel());
        PilotModel pilot = pilotService.getPilotDetailByLicenseNumber(licenseNumber);
        pilot.setPilotFlight(listOfFlights);
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"addFlight"}, method = RequestMethod.POST)
    public String addRow(@ModelAttribute PilotModel pilot, Model model) {
        if (pilot.getPilotFlight() == null) {
            pilot.setPilotFlight(new ArrayList<FlightModel>());
        }
        pilot.getPilotFlight().add(new FlightModel());

        model.addAttribute("pilot", pilot);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"delete"}, method = RequestMethod.POST)
    public String removeRow(@ModelAttribute PilotModel pilot, Model model, HttpServletRequest req) {
        int index = Integer.parseInt(req.getParameter("delete"));
        pilot.getPilotFlight().remove(index);
        model.addAttribute("pilot", pilot);
        return "addFlight";
    }

    @RequestMapping(value = "/flight/add/{licenseNumber}", params = {"save"}, method = RequestMethod.POST)
    private String addFlightSumbit(@ModelAttribute PilotModel pilot, Model model) {
        PilotModel pilotPointer = pilotService.getPilotById(pilot.getId());
        for (FlightModel flight : pilot.getPilotFlight()) {
            flight.setPilot(pilotPointer);
            flightService.addFlight(flight);
        }

        return "add";
    }


    @RequestMapping(value = "/flight/delete/", method = RequestMethod.POST)
    private String deleteFlight(@ModelAttribute PilotModel pilot, Model model) {
        for (FlightModel flight : pilot.getPilotFlight()) {
            flightService.deleteFlight(flight.getId());
        }
        return "deleted";
    }

    @RequestMapping(value = "/flight/update/{flightId}", method = RequestMethod.POST)
    private String updateFlight(@PathVariable(value = "flightId") long flightId, @ModelAttribute FlightModel flightModel) {
        flightModel.setId(flightId);
        flightService.addFlight(flightModel);

        return "updated";
    }

    @RequestMapping(value = "/flight/update/{flightId}", method = RequestMethod.GET)
    private String updateFlight(@PathVariable(value = "flightId") long flightId, Model model) {
        model.addAttribute("flight", flightService.findById(flightId));
        return "update-flight";
    }

    @RequestMapping(value = "/flight/all", method = RequestMethod.GET)
    private String viewFlight(Model model) {
        model.addAttribute("flights", flightService.findAll());
        return "list-flight";
    }

}

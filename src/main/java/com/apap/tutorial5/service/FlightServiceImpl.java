package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;
import com.apap.tutorial5.repository.FlightDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightDB flightDB;

    @Override
    public void addFlight(FlightModel flight) {
        flightDB.save(flight);
    }

    @Override
    public void deleteFlight(long id) {
        flightDB.deleteById(id);
    }

    @Override
    public FlightModel findById(long id) {
        return flightDB.findById(id).get();
    }

    @Override
    public List<FlightModel> findAll() {
        return flightDB.findAll();
    }
}

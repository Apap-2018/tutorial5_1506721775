package com.apap.tutorial5.service;

import com.apap.tutorial5.model.FlightModel;

import java.util.List;

public interface FlightService {
    void addFlight(FlightModel flight);

    void deleteFlight(long id);

    FlightModel findById(long id);

    List<FlightModel> findAll();
}

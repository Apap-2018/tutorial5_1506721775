package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;

public interface PilotService {
    PilotModel getPilotDetailByLicenseNumber(String licensenumber);

    void addPilot(PilotModel pilot);

    void deletePilot(Long id);

    PilotModel getPilotById(Long id);
}
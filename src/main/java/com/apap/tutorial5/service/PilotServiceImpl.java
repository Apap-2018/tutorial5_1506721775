package com.apap.tutorial5.service;

import com.apap.tutorial5.model.PilotModel;
import com.apap.tutorial5.repository.PilotDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PilotServiceImpl implements PilotService {

    @Autowired
    private PilotDB pilotDB;

    @Override
    public PilotModel getPilotDetailByLicenseNumber(String licensenumber) {
        return pilotDB.findByLicenseNumber(licensenumber);
    }

    @Override
    public void addPilot(PilotModel pilot) {
        pilotDB.save(pilot);

    }

    @Override
    public void deletePilot(Long id) {
        pilotDB.deleteById(id);
    }

    @Override
    public PilotModel getPilotById(Long id) {
        return pilotDB.findById(id).get();
    }
}

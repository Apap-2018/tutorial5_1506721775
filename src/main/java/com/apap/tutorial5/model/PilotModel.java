package com.apap.tutorial5.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pilot")
public class PilotModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public PilotModel(){ };

    public PilotModel(@NotNull @Size(max = 50) String licenseNumber, @NotNull @Size(max = 50) String name, @NotNull int flyhour, List<FlightModel> pilotFlight) {
        this.licenseNumber = licenseNumber;
        this.name = name;
        this.flyhour = flyhour;
        this.pilotFlight = pilotFlight;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFlyhour() {
        return flyhour;
    }

    public void setFlyhour(int flyhour) {
        this.flyhour = flyhour;
    }

    public List<FlightModel> getPilotFlight() {
        return pilotFlight;
    }

    public void setPilotFlight(List<FlightModel> pilotFlight) {
        this.pilotFlight = pilotFlight;
    }

    @NotNull
    @Size(max = 50)
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "flyhour", nullable = false)
    private int flyhour;

    @OneToMany(mappedBy = "pilot", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<FlightModel> pilotFlight;

}

package com.apap.tutorial5.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "flight")
public class FlightModel implements Serializable {
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public PilotModel getPilot() {
        return pilot;
    }

    public void setPilot(PilotModel pilot) {
        this.pilot = pilot;
    }

    public FlightModel() {
    }

    ;

    public FlightModel(@NotNull @Size(max = 50) String flightNumber, @NotNull @Size(max = 50) String origin, @NotNull @Size(max = 50) String destination, @NotNull Date time, PilotModel pilot) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.pilot = pilot;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "flight_number", nullable = false)
    private String flightNumber;

    @NotNull
    @Size(max = 50)
    @Column(name = "origin", nullable = false)
    private String origin;

    @NotNull
    @Size(max = 50)
    @Column(name = "destination", nullable = false)
    private String destination;

    @NotNull
    @Column(name = "time")
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pilot_licenseNumber", referencedColumnName = "license_number", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private PilotModel pilot;

}

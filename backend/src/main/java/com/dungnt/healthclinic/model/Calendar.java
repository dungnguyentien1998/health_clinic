package com.dungnt.healthclinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "calendars")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @Column(name = "time")
    private Time time;

    @Column(name = "state")
    private Integer state;

    @Column(name = "room")
    private String room;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", referencedColumnName = "id")
//    @JsonBackReference
    private ClinicService clinicService;

    @OneToOne(mappedBy = "calendar")
//    @JsonManagedReference
    private Appointment appointment;

    public Calendar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getClinicServiceId() {
        return clinicService.getId();
    }

    public String getClinicServiceName() {
        return clinicService.getName();
    }

    public String getClinicServiceDescription() {
        return clinicService.getDescription();
    }

    @JsonIgnore
    public ClinicService getClinicService() {
        return clinicService;
    }

    @JsonIgnore
    public void setClinicService(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}

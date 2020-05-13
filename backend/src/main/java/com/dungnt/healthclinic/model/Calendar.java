package com.dungnt.healthclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "calendars")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JsonIgnore
    private ClinicService clinicService;

//    @OneToOne(mappedBy = "calendar")
//    private Appointment appointment;

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

    public ClinicService getClinicService() {
        return clinicService;
    }

    public void setClinicService(ClinicService clinicService) {
        this.clinicService = clinicService;
//        clinicService.getCalendars().add(this);
    }

//    public Appointment getAppointment() {
//        return appointment;
//    }
//
//    public void setAppointment(Appointment appointment) {
//        this.appointment = appointment;
//    }
}

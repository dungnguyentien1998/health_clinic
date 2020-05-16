package com.dungnt.healthclinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "calendar_id", referencedColumnName = "id", unique = true)
//    @JsonBackReference
    private Calendar calendar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", referencedColumnName = "id")
//    @JsonBackReference
    private User client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_staff_id", referencedColumnName = "id")
//    @JsonBackReference
    private User medicalStaff;

//    @OneToOne(mappedBy = "appointment")
//    private Survey survey;

    public Appointment() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCalendarId() {
        return calendar.getId();
    }

    public Date getCalendarDate() {
        return calendar.getDate();
    }

    public Time getCalendarTime() {
        return calendar.getTime();
    }

    public Integer getCalendarState() {
        return calendar.getState();
    }

    public String getCalendarRoom() {
        return calendar.getRoom();
    }

    public Integer getClinicServiceId() {
        return calendar.getClinicServiceId();
    }

    public String getClinicServiceName() {
        return calendar.getClinicServiceName();
    }

    public String getClinicServiceDescription() {
        return calendar.getClinicServiceDescription();
    }

    @JsonIgnore
    public Calendar getCalendar() {
        return calendar;
    }

    @JsonIgnore
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    @JsonIgnore
    public User getClient() {
        return client;
    }

    @JsonIgnore
    public void setClient(User client) {
        this.client = client;
    }

    public Integer getClientId() {
        return client.getId();
    }

    @JsonIgnore
    public User getMedicalStaff() {
        return medicalStaff;
    }

    @JsonIgnore
    public void setMedicalStaff(User medicalStaff) {
        this.medicalStaff = medicalStaff;
    }

    public Integer getMedicalStaffId() {
        return medicalStaff.getId();
    }
}

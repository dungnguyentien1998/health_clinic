package com.dungnt.healthclinic.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "appointments")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCalendarId() {
        return calendar.getId();
    }

    public LocalDate getCalendarDate() {
        return calendar.getDate();
    }

    public LocalTime getCalendarTimeStart() {
        return calendar.getTimeStart();
    }

    public LocalTime getCalendarTimeEnd() {
        return calendar.getTimeEnd();
    }

    public Integer getCalendarState() {
        return calendar.getState();
    }

    public String getCalendarRoom() {
        return calendar.getRoom();
    }

    public Long getClinicServiceId() {
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

    public Long getClientId() {
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

    public Long getMedicalStaffId() {
        return medicalStaff.getId();
    }
}

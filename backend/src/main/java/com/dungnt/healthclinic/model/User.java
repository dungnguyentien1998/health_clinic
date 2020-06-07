package com.dungnt.healthclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "country")
    private String country;
    @Column(name = "email")
    private String email;
    @Column(name = "state")
    private String state;
    @Column(name = "phone")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "privilege")
    private Integer privilege;
    @Column(name = "room")
    private String room;

    @ManyToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns={@JoinColumn(name="user_id", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="role_id", referencedColumnName="id")})
    private Set<Role> roles = new HashSet<>();

    @JsonIgnore
    public Set<Role> getRoles() {
        return roles;
    }

    @JsonIgnore
    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<String> getRoleNames() {
        Set<String> roleNames = new HashSet<>();
        for (Role role: roles) {
            roleNames.add(role.getName());
        }
        return roleNames;
    }

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> clientAppointments = new HashSet<>();

    @OneToMany(mappedBy = "medicalStaff", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Appointment> medicalStaffAppointments = new HashSet<>();


    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Integer privilege) {
        this.privilege = privilege;
    }

    public Set<Appointment> getClientAppointments() {
        return clientAppointments;
    }

    public void setClientAppointments(Set<Appointment> clientAppointments) {
        this.clientAppointments = clientAppointments;
    }

    public Set<Appointment> getMedicalStaffAppointments() {
        return medicalStaffAppointments;
    }

    public void setMedicalStaffAppointments(Set<Appointment> medicalStaffAppointments) {
        this.medicalStaffAppointments = medicalStaffAppointments;
    }
}

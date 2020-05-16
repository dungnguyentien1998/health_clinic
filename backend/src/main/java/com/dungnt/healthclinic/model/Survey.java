package com.dungnt.healthclinic.model;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id", unique = true)
    private Appointment appointment;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "survey_question",
            joinColumns = @JoinColumn(name = "survey_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"))
    private Set<Question> questions;

    public Survey() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

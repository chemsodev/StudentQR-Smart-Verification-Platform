package com.project.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;


@Entity
@Table(name = "speciality")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})  // Ignore Hibernate specific properties
public class Speciality {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "palier")
    private String palier;

    @Column(name = "specialite")
    private String specialite;


    public Speciality(String palier, String specialite) {
        this.palier = palier;
        this.specialite = specialite;
    }

    public Speciality() {}

    public String getPalier() {
        return palier;
    }

    public void setPalier(String palier) {
        this.palier = palier;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

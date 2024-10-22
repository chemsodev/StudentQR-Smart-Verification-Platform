package com.project.model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "matricule")
    private String matricule;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "etat")
    private String etat;

    @Column(name = "groupe_td")
    private String groupe_td;

    @ManyToOne(fetch = FetchType.LAZY) // Foreign key to Speciality
    @JoinColumn(name = "speciality_id", referencedColumnName = "id")
    @JsonBackReference
    private Speciality speciality;

    @ManyToOne(fetch = FetchType.LAZY) // Foreign key to Section
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    @JsonBackReference
    private Section section;

    @Column(name = "email_1")
    private String email_1;

    @Column(name = "email_2")
    private String email_2;

    public Student() {}

    public Student(String nom, String prenom, String matricule, String email_1, String email_2, String groupe_td,String etat) {
        this.nom = nom;
        this.prenom = prenom;
        this.groupe_td = groupe_td;
        this.matricule = matricule;
        this.email_1 = email_1;
        this.email_2 = email_2;
        this.etat = etat;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getEmail_1() {
        return email_1;
    }

    public void setEmail_1(String email_1) {
        this.email_1 = email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public void setEmail_2(String email_2) {
        this.email_2 = email_2;
    }

    public String getGroupe_td() {
        return groupe_td;
    }

    public void setGroupe_td(String groupe_td) {
        this.groupe_td = groupe_td;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

}

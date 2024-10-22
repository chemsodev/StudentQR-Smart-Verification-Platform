package com.project.model;
public class ResponseData {
    private String message;
    private int status;
    private String matricule;
    private String firstname;
    private String lastname;
    private String email1;
    private String email2;
    private String etat;
    private String specialityName;
    private String specialityYear;
    private String sectionName;

    // Constructors
    public ResponseData() {}

    public ResponseData(String message, int status, String matricule, String firstname, String lastname, String email1, String email2, String specialityName, String specialityYear, String sectionName, String etat) {
        this.message = message;
        this.status = status;
        this.matricule = matricule;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email1 = email1;
        this.email2 = email2;
        this.specialityName = specialityName;
        this.specialityYear = specialityYear;
        this.sectionName = sectionName;
        this.etat = etat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail1() {
        return email1;
    }

    public void setEmail1(String email1) {
        this.email1 = email1;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getSpecialityName() {
        return specialityName;
    }

    public void setSpecialityName(String specialityName) {
        this.specialityName = specialityName;
    }

    public String getSpecialityYear() {
        return specialityYear;
    }

    public void setSpecialityYear(String specialityYear) {
        this.specialityYear = specialityYear;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }
}

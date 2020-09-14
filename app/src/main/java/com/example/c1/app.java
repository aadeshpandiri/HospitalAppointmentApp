package com.example.c1;

public class app {

    String department;
    String clinic;
    String doctor;
    String phoneno;

    public app() {
    }

    public app(String department, String clinic, String doctor, String phoneno) {
        this.department = department;
        this.clinic = clinic;
        this.doctor = doctor;
        this.phoneno = phoneno;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}

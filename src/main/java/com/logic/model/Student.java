package com.logic.model;

import com.logic.model.School;

public class Student {
    int id;
    private String name;
    private String surName;
    private String patronomyc;
    private int yearp;
    private int yearv;
    private String schoolName;
    private String town;
    private School school;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setPatronomyc(String patronomyc) {
        this.patronomyc = patronomyc;
    }

    public void setYearp(int yearp) {
        this.yearp = yearp;
    }

    public void setYearv(int yearv) {
        this.yearv = yearv;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public Student(String name, String surName, String patronomyc, int yearp, int yearv, School school) {
        this.name = name;
        this.surName = surName;
        this.patronomyc = patronomyc;
        this.yearp = yearp;
        this.yearv = yearv;
        this.school = school;
        this.schoolName = school.getNameSchool();
        this.town = school.getTown();
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getTown() {
        return town;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Student(int id, String name, String surName, String patronomyc, int yearp, int yearv, School school) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.patronomyc = patronomyc;
        this.yearp = yearp;
        this.yearv = yearv;
        this.school = school;
        this.schoolName = school.getNameSchool();
        this.town = school.getTown();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getPatronomyc() {
        return patronomyc;
    }

    public int getYearp() {
        return yearp;
    }

    public int getYearv() {
        return yearv;
    }

    public School getSchool() {
        return school;
    }
}

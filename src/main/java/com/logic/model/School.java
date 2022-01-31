package com.logic.model;

public class School {
    int idSchool;
    String nameSchool;
    String town;

    public School(String nameSchool, String town) {
        this.nameSchool = nameSchool;
        this.town = town;
    }

    public void setIdSchool(int idSchool) {
        this.idSchool = idSchool;
    }

    public void setNameSchool(String nameSchool) {
        this.nameSchool = nameSchool;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public int getIdSchool() {
        return idSchool;
    }

    public String getNameSchool() {
        return nameSchool;
    }

    public String getTown() {
        return town;
    }

    public School(int idSchool, String nameSchool, String town) {
        this.idSchool = idSchool;
        this.nameSchool = nameSchool;
        this.town = town;
    }
}

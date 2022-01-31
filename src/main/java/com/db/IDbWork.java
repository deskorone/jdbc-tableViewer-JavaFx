package com.db;

import com.logic.model.School;
import com.logic.model.Student;

public interface IDbWork{
    boolean add(Student student);
    void editSchool(int id,String schoolname, String town);
    void deleteWhereSchoolEmpty();
    int getSchoolRating(School school);
}

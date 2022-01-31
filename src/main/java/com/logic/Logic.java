package com.logic;

import com.db.DbWork;
import com.logic.model.School;
import com.logic.model.Student;

import java.util.ArrayList;
public class Logic {
        ArrayList<Student> list;
        public Logic(){
                try(DbWork work = new DbWork()){
                        list = work.getStudentList();
                }catch (Exception e){
                        e.printStackTrace();
                }
        }

        public boolean addStudent(Student student){
                try(DbWork work = new DbWork()){
                  work.add(student);
                  list.add(getLast());
                  return true;
                }catch (Exception e){
                        e.printStackTrace();
                        return false;
                }
        }

        public void print(){
                for(Student i : list){
                        System.out.println(i.getId());
                }
        }

        public ArrayList<Student> delete(){
                try(DbWork work = new DbWork()){
                        work.deleteWhereSchoolEmpty();
                        return list = work.getStudentList();
                }catch (Exception e){
                        e.printStackTrace();
                        return null;
                }
        }

        public int getRating(School school){
                try(DbWork work = new DbWork()){
                        return work.getSchoolRating(school);
                }catch (Exception e){
                        e.printStackTrace();
                        return 0;
                }
        }

        public Student getLast(){
                try(DbWork work = new DbWork()){
                        return work.getStudentLast();
                }catch (Exception e){
                        e.printStackTrace();
                        return null;
                }
        }

        public void edding(int id,String schoolname, String town){
                try(DbWork dbWork = new DbWork()){
                        for(Student i : list){
                                if(i.getSchool().getIdSchool() == id){
                                        i.setSchoolName(schoolname);
                                        i.setTown(town);
                                        i.getSchool().setNameSchool(schoolname);
                                        i.getSchool().setTown(town);
                                }
                        }
                        dbWork.editSchool(id, schoolname,town);
                }catch (Exception e){
                        e.printStackTrace();
                }
        }

        public ArrayList<Student> getList() {
                return list;
        }


        public boolean checkStudingYear(Student student, int year){
                for(int i = student.getYearp(); i < student.getYearv(); i++){
                        if(i == year){
                                return true;
                        }
                }
                return false;
        }

        public ArrayList<Student> getArrayListYear(int year, School school){
                ArrayList<Student> arr = new ArrayList<>();
                for(Student i : list){
                        if(i.getSchool().getNameSchool().equals(school.getNameSchool()) && i.getSchool().getTown().equals(school.getTown())){
                                if(checkStudingYear(i, year)){
                                arr.add(i);
                                }
                        }
                }
                return arr;
        }



}

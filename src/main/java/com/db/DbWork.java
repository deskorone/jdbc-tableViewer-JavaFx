package com.db;

import com.logic.model.School;
import com.logic.model.Student;

import java.sql.*;
import java.util.ArrayList;


public class DbWork implements AutoCloseable, IDbWork{
    Connection connection;
    ArrayList<Student> list = new ArrayList<>();


    public DbWork() throws SQLException{
        try{
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        this.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/laba5", "user", "pass");
    }


    @Override
    public void close() throws Exception {
        connection.close();
    }

    public static void main(String[] args) {
        try(DbWork work  = new DbWork()){
            work.deleteWhereSchoolEmpty();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private boolean schoolCheck(String name, String town){
        try(PreparedStatement statement = connection.prepareStatement("SELECT *FROM school WHERE EXISTS(SELECT *FROM school WHERE name=? and town=?);")){
            statement.setString(1, name);
            statement.setString(2, town);
            try(ResultSet set = statement.executeQuery()){
                if(set.next()){
                    return true;
                }
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean add(Student student) {
        String name = student.getName().trim();
        String surname = student.getSurName().trim();
        String patronymic = student.getPatronomyc().trim();
        int yearp = student.getYearp();
        int yearv = student.getYearv();
        String schoolname = student.getSchool().getNameSchool().trim();
        String town = student.getSchool().getTown();
        if(!schoolCheck(schoolname, town)) {
            String sql = "INSERT INTO school(name,town) VALUES (?,?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, schoolname);
                statement.setString(2, town);
                statement.execute();
            } catch (SQLException e) {
                return false;
            }
        }
        try(CallableStatement callableStatement = connection.prepareCall("call insert_data(?,?,?,?,?,?,?)")){
            callableStatement.setString(1, name);
            callableStatement.setString(2, surname);
            callableStatement.setString(3, patronymic);
            callableStatement.setInt(4, yearp);
            callableStatement.setInt(5, yearv);
            callableStatement.setString(6, schoolname);
            callableStatement.setString(7, town);
            callableStatement.execute();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }


    public ArrayList<Student> getStudentList(){
        ArrayList<Student> list = new ArrayList<>();
        try(Statement statement = connection.createStatement()){
            try(ResultSet set = statement.executeQuery("SELECT * FROM users JOIN school ON users.schoolid=school.id;")){
                while(set.next()){
                    list.add(new Student(set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getInt(5),
                            set.getInt(6),
                            new School(set.getInt(8), set.getString(9), set.getString(10))
                    ));
                }
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }

    public Student getStudentLast(){
        try(Statement statement = connection.createStatement()){
            try(ResultSet set = statement.executeQuery("SELECT * FROM users JOIN school ON users.schoolid=school.id ORDER BY users.id DESC limit 1;")){
                if(set.next()){
                    return  new Student(set.getInt(1),
                            set.getString(2),
                            set.getString(3),
                            set.getString(4),
                            set.getInt(5),
                            set.getInt(6),
                            new School(set.getInt(8), set.getString(9), set.getString(10)));
                }
            }
            return null;

        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }



    @Override
    public void editSchool(int id,String schoolname, String town) {
        if(!schoolCheck(schoolname, town)) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE school SET name=?,town=? WHERE id=?;")) {
                statement.setString(1, schoolname);
                statement.setString(2, town);
                statement.setInt(3, id);
                statement.execute();
                System.out.println("WORK");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteWhereSchoolEmpty() {
        try(Statement statement = connection.createStatement()){
            statement.execute("DELETE FROM users WHERE schoolid IN (SELECT id FROM school WHERE name='' or town='');");
        }catch (SQLException e) {
            System.out.println(e);
        }

    }


    public School getSchool(School school){
        String name = school.getNameSchool();
        String town = school.getTown();
        try(PreparedStatement statement = connection.prepareStatement("SELECT * FROM school WHERE name=? and town=?;")){
            statement.setString(1, name);
            statement.setString(2, town);
            try(ResultSet set = statement.executeQuery()){
                if(set.next()) {
                    return new School(set.getInt(1), set.getString(2), set.getString(3));
                }
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public int getSchoolRating(School school) {
        String name = school.getNameSchool().trim();
        String town = school.getTown().trim();
        int count = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM users WHERE schoolid=(SELECT id FROM school WHERE name=? and town=?);")){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, town);
            try(ResultSet set = preparedStatement.executeQuery()){
                while(set.next()){
                    count++;
                }
            }
            return count;
        }catch (SQLException e ){
            return 0;
        }
    }
}


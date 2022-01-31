package com.ui.fivelaba;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.logic.Logic;
import com.logic.model.School;
import com.logic.model.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UiControllers {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private  ObservableList<Student> list = FXCollections.observableArrayList();

    @FXML
    private Button openBut;

    @FXML
    private TableView<Student> table;

    @FXML
    private TableColumn<Student, String> nameC;

    @FXML
    private TableColumn<Student, String> surnameC;

    @FXML
    private TableColumn<Student, String> patrC;

    @FXML
    private TableColumn<Student, Integer> yearP;

    @FXML
    private TableColumn<Student, Integer> yearE;

    @FXML
    private TableColumn<Student, String> schoolC;

    @FXML
    private TableColumn<Student,String> townC;

    @FXML
    private Button addBut;

    @FXML
    private TextField nameArea;

    @FXML
    private TextField surnameArea;

    @FXML
    private TextField patrArea;

    @FXML
    private TextField yearvArea;

    @FXML
    private TextField townArea;

    @FXML
    private TextField schoolNameArera;

    @FXML
    private TextField yearpArea;

    @FXML
    private Label n;

    @FXML
    private Label n1;

    @FXML
    private Label n2;

    @FXML
    private Label n3;

    @FXML
    private Label n4;

    @FXML
    private Label n5;

    @FXML
    private Label n6;

    @FXML
    private Button eddingBut;

    @FXML
    private Label eddingLabel;

    @FXML
    private Label incorrectLabel;

    @FXML
    private Button doneBut;

    @FXML
    private TextField ratingArea;

    @FXML
    private Button getBut;

    @FXML
    private Button delBut;

    private boolean done = false;

    private final Logic logic;
    //java -jar D:\Fivelaba\out\artifacts\Fivelaba_jar\Fivelaba.jar
    {
        logic = new Logic();
    }

    private Student tmpstudent;

    ObservableList<Student> singleP;

    @FXML
    private Button getYearBut;

    @FXML
    void initialize() {
        doneBut.setVisible(false);
        addBut.setVisible(false);
        delBut.setVisible(false);
        eddingBut.setVisible(false);
        incorrectLabel.setVisible(false);
        getBut.setVisible(false);
        getYearBut.setVisible(false);
        eddingLabel.setVisible(false);
        openBut.setOnAction(event -> {
            table.getItems().clear();
            nameC.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
            surnameC.setCellValueFactory(new PropertyValueFactory<Student, String>("surName"));
            patrC.setCellValueFactory(new PropertyValueFactory<Student, String>("patronomyc"));
            yearP.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearp"));
            yearE.setCellValueFactory(new PropertyValueFactory<Student, Integer>("yearv"));
            schoolC.setCellValueFactory(new PropertyValueFactory<Student, String>("schoolName"));
            townC.setCellValueFactory(new PropertyValueFactory<Student, String>("town"));
            table.setItems(getList(logic.getList()));
            openBut.setVisible(false);
            doneBut.setVisible(true);
            addBut.setVisible(true);
            delBut.setVisible(true);
            eddingBut.setVisible(true);
            getBut.setVisible(true);
            getYearBut.setVisible(true);

        });

        addBut.setOnAction(event -> {
            if(checkArea()){
                incorrectLabel.setVisible(false);
                add(new Student(nameArea.getText(), surnameArea.getText(), patrArea.getText(),
                        Integer.parseInt(yearpArea.getText()), Integer.parseInt(yearvArea.getText()),  new School(schoolNameArera.getText(), townArea.getText())));
            }else {
                incorrectLabel.setVisible(true);
                System.out.println("FALSE");
            }
        });

        doneBut.setOnAction(event -> {
            if(done){
                tmpstudent.getSchool().setNameSchool(schoolNameArera.getText());
                tmpstudent.getSchool().setTown(townArea.getText());
                tmpstudent.setSchoolName(schoolNameArera.getText());
                tmpstudent.setTown(townArea.getText());
                System.out.println(tmpstudent.getSchool().getNameSchool());
                logic.edding(tmpstudent.getSchool().getIdSchool(), schoolNameArera.getText(), townArea.getText());
                table.getItems().clear();
                list = getList(logic.getList());
                done = false;
                eddingLabel.setVisible(false);
            }
        });

        eddingBut.setOnAction(event -> {
            eddingLabel.setVisible(true);
            singleP = table.getSelectionModel().getSelectedItems();
            schoolNameArera.setText(singleP.get(0).getSchool().getNameSchool());
            townArea.setText(singleP.get(0).getSchool().getTown());
            tmpstudent = singleP.get(0);
            done = true;
        });

        getBut.setOnAction(actionEvent -> {

            singleP = table.getSelectionModel().getSelectedItems();
            tmpstudent = singleP.get(0);
            ratingArea.setText(Integer.toString(logic.getRating(tmpstudent.getSchool())));

        });

        delBut.setOnAction(actionEvent -> {
            logic.delete();
            table.getItems().clear();
            list = getList(logic.getList());
        });

        getYearBut.setOnAction(actionEvent -> {
            if(!schoolNameArera.getText().trim().equals("") && !townArea.getText().trim().equals("")){
                table.getItems().clear();
                list = getList(logic.getArrayListYear(Integer.parseInt(yearpArea.getText()), new School(schoolNameArera.getText().trim(), townArea.getText().trim())));
                openBut.setVisible(true);
            }
        });


    }

    private boolean add(Student student){
        if(logic.addStudent(student)){
            list.add(logic.getLast());
            return true;
        }else {
            return false;
        }
    }

    @FXML
    private Student getStudentById(int id){
        for(Student i : list){
            if(id == i.getId()){
                return i;
            }
        }
        return null;
    }


    @FXML
    private ObservableList<Student> getList(ArrayList<Student> lister){
        if(logic.getList() != null) {
            for (Student i : lister) {
                list.add(i);
            }
        }else{
            list.add(new Student("","","", 0, 0 ,new School("", "")));
        }
        return list;
    }



    private boolean checkArea(){
        if(nameArea.getText() == null || nameArea.getText() == "" && surnameArea.getText() == null || surnameArea.getText().trim() == "" && patrArea.getText() == null || patrArea.getText().trim() == ""
                && yearpArea.getText() == null || !checkNumFormat(yearpArea.getText().trim())
                && yearvArea.getText() == null || !checkNumFormat(yearvArea.getText().trim())){
            return false;
        }else {
            if(Integer.parseInt(yearvArea.getText().trim()) < Integer.parseInt(yearpArea.getText())){
                return false;
            }
            return true;
        }
    }



    private boolean checkNumFormat(String i){
        try{
            Integer.parseInt(i);
        }catch (NumberFormatException e){
            return false;
        }
        return true;
    }

}

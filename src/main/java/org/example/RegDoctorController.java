package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegDoctorController {
    @FXML
    Button addButton_addDoctor;

    @FXML
    TextField surname_add_doctor;

    @FXML
    TextField name_loginDoctor_field;
    @FXML
    TextField phone_loginDoctor_field;
    @FXML
    public void addDoctorButtonAction()
    {
     addButton_addDoctor.setOnAction(event ->
     {

     });
    }
}

package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicBoolean;

public class DoctorController {
    @FXML
    TextField surname_add_doctor;
    @FXML
    TextField name_login_field;
    @FXML
    TextField phone_add_doctor;
    @FXML
    Button addButton_addDoctor;
    public boolean checkPhone(String phone)
    {

        for(Person person: PrimaryController.getPeople())
        {
            if(person.phone.equals(phone))
                return true;
        }
        return false;
    }
    public void addButtonAction()
    {
        addButton_addDoctor.setOnAction(actionEvent ->
        {
            String surname = surname_add_doctor.getText();
            String name = name_login_field.getText();
            String phone = phone_add_doctor.getText();
            AtomicBoolean flagAllOk = new AtomicBoolean(true);

            if(!phone.startsWith("7") || phone.length() != 11)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Неверный формат номера телефона");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(checkPhone(phone))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Такой номер уже зарегистрирован");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(name.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Введите имя");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(surname.isEmpty())
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Введите фамилию");
                alert.showAndWait();
                flagAllOk.set(false);
            }
        });
    }
}

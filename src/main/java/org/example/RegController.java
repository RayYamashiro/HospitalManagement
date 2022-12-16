package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class RegController {
    @FXML
    TextField reg_login_field;
    @FXML
    TextField reg_password_field;
    @FXML
    TextField name_login_field;
    @FXML
    TextField surname_login_field;
    @FXML
    TextField phone_login_field;
    @FXML
    RadioButton male_login_radioBut;
    @FXML
    RadioButton female_login_radioBut;
    @FXML
    Button reg_Button;
    @FXML
    ToggleGroup humanSex;

    @FXML
    Button backButton;
    public boolean checkEmail(String email)
    {

        for(Person person: PrimaryController.getPeople())
        {
            if(person.login.equals(email))
                return true;
        }
        return false;
    }

    public boolean checkPhone(String phone)
    {

        for(Person person: PrimaryController.getPeople())
        {
            if(person.phone.equals(phone))
                return true;
        }
        return false;
    }

    @FXML
    public void backButtonAction()
    {
        backButton.setOnAction(event ->
        {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        });
    }
    @FXML
    public void regButtonAction()
    {


        reg_Button.setOnAction(event ->
        {

            String email = reg_login_field.getText();
            String password = reg_password_field.getText();
            String name = name_login_field.getText();
            String surname = surname_login_field.getText();
            String phone = phone_login_field.getText();
            AtomicReference<Toggle> current = new AtomicReference<>();
            AtomicReference<Person.Sex> finalCurrentSex = new AtomicReference<>();
            AtomicBoolean flagAllOk = new AtomicBoolean(true);

            if(!email.contains("@"))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Вы ввели неверный логин");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(checkEmail(email))
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Такая почта уже существует");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(password.length() < 6)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ваш пароль имеет длину " + password.length() + ", необходимая минимальная длина - 6");
                alert.showAndWait();
                flagAllOk.set(false);
            }
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
            if(humanSex.getSelectedToggle() == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Выберите свой пол");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            else
            {
                current.set(humanSex.getSelectedToggle());
                String sex = current.toString();
                Person.Sex currentSex = Person.Sex.Мужчина;
                if(sex.contains("Женщина"))
                    currentSex = Person.Sex.Женщина;
                finalCurrentSex.set(currentSex);
            }

            if(email == null || name == null || password == null || surname == null || phone == null)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Заполнены не все поля");
                alert.showAndWait();
                flagAllOk.set(false);
            }
            if(flagAllOk.get())
            {
                org.hibernate.cfg.Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
                SessionFactory sessionFactory = configuration.buildSessionFactory();
                Session session = sessionFactory.getCurrentSession();
                try {
                    session.beginTransaction();
                    Person person = new Person(name , surname, email, password , phone, finalCurrentSex.get());
                    session.save(person);

                    session.getTransaction().commit();
                }finally {
                    sessionFactory.close();
                }

                Stage stage = (Stage) reg_Button.getScene().getWindow();
                stage.close();
            }
        });
    }
}

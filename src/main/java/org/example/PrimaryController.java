package org.example;

import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class PrimaryController {

    @FXML
    private Button authSignInButton;
    @FXML
    private TextField login_field;
    @FXML
    private PasswordField password_field;
    @FXML
    private Button loginSignUpButton;
    private static List<Person> people;
    static {
        org.hibernate.cfg.Configuration configuration = new Configuration().addAnnotatedClass(Person.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        people = session.createQuery("FROM Person").getResultList();
        session.getTransaction().commit();
        sessionFactory.close();
    }

    public static List<Person> getPeople() {
        return people;
    }

    public static void setPeople(List<Person> people) {
        PrimaryController.people = people;
    }

    @FXML
    void initialize()
    {
        loginSignUpButton.setOnAction(event ->
        {
            loginSignUpButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("registration.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
            /*
            FXMLLoader loader1 = new FXMLLoader();
            loader1.setLocation(getClass().getResource("startpage.fxml"));
            try {
                loader1.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Parent root1 = loader1.getRoot();
            Stage stage1 = new Stage();
            stage1.setScene(new Scene(root1));
            stage1.showAndWait();

             */
        });


    }


    @FXML
    private void authButtonAction()
    {
        String login = login_field.getText();
        String pass = password_field.getText();

        boolean existFlag = false;

            if(login.equals("admin") && pass.equals("admin"))
            {
                authSignInButton.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("adminMainPage.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();
            } else  {
                for (Person p : people)
                {
                    if(p.login.equals(login) && p.password.equals(pass))
                    {
                        mainPersonController.setPerson_id(p.getId());
                        existFlag = true;
                        break;
                    }
                }

                if(existFlag == false)
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Вы ввели неверный логин или пароль");
                    alert.showAndWait();
                }else
                {
                    authSignInButton.getScene().getWindow().hide();

                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("mainPersonPage.fxml"));

                    try {
                        loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Parent root = loader.getRoot();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.showAndWait();
                }
            }

    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("registration");
    }
}

package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class AdminMainController {
    @FXML
    Button add_doctor_admin;
    @FXML
    Button delete_doctor_admin;
    private static List<Doctor> doctors;
    static {
        org.hibernate.cfg.Configuration configuration = new Configuration().addAnnotatedClass(Doctor.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        doctors = session.createQuery("FROM Doctor ").getResultList();
        session.getTransaction().commit();
        sessionFactory.close();
    }
    public void addDoctorButtonAction()
    {
        add_doctor_admin.setOnAction(event ->
        {
            add_doctor_admin.getScene().getWindow().hide();

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
        });
    }
}

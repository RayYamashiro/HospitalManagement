module org.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;
    opens org.example to javafx.fxml;
    exports org.example;
}

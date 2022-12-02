package org.example;
import jakarta.persistence.*;

@Entity
@Table(name="Person")
public class Person {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="surname")
    public String surname;

    @Column(name="login")
    public String login;

    @Column(name="password")
    public String password;

    @Column(name="phone")
    public String phone;

    @Enumerated(EnumType.STRING)
    @Column(name="sex")
    public  Sex sex;

    public Person(){}

    public Person( String name, String surname, String login, String password, String phone, Sex sex) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public enum Sex
    {
        Мужчина , Женщина;
    }
}

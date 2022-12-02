package org.example;

import jakarta.persistence.*;

@Entity
@Table(name="Doctor")
public class Doctor {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    public int id;

    @Column(name="name")
    public String name;

    @Column(name="surname")
    public String surname;

    @Column(name="phone")
    public String phone;

    @Enumerated(EnumType.STRING)
    @Column(name="specialization")
    public Doctor.Specialization spec;

    public Doctor(){}

    public Doctor(String name, String surname, String phone, Specialization spec) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.spec = spec;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Specialization getSpec() {
        return spec;
    }

    public void setSpec(Specialization spec) {
        this.spec = spec;
    }

    public enum Specialization
    {
        Терапевт, Хирург, Гинеколог, Невролог, Оториноларинголог, Уролог, Психиатр_нарколог,Педиатр ;
    }
}

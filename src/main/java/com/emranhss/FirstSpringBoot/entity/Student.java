package com.emranhss.FirstSpringBoot.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @Column(nullable = false, length = 40, name = "studentName")
    private String name;
    @Column(nullable = false, unique = true)
    private  String email;

    @Column(nullable = false, unique = true)
    private  String cell;
    private  String gender;

    private Date dob;

    public Student() {
    }

    public Student(int id) {
        this.id = id;
    }

    public Student(String name, String email, String cell, String gender, Date dob) {
        this.name = name;
        this.email = email;
        this.cell = cell;
        this.gender = gender;
        this.dob = dob;
    }

    public Student(int id, String email, String name, String cell, String gender, Date dob) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.cell = cell;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}

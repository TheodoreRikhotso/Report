package com.example.admin.report;

import java.io.Serializable;

/**
 * Created by Theodore on 2017/08/09.
 */

public class Person implements Serializable {
    private int id,grade,year,month,day,contact;
    private String name,surname,password,email,role;
    private byte[] photo;

    public Person() {
    }

    public Person(int id, int grade, int year, int month, int day, String name, String surname, String password, String email, String role, int contact) {
        this.id = id;
        this.grade = grade;
        this.year = year;
        this.month = month;
        this.day = day;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.role = role;
        this.contact = contact;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }
}

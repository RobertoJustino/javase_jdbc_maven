package com.hitema.modeles;

import java.time.LocalDate;

/**
 * POJO : Plain Old Java Object - Bon vieux objet Java
 */
public class Student extends Person{
    private String cursus;

    public Student() {
    }

    public Student(Integer id, String lastName, String firstName, LocalDate birthDate, String genre, String email, String cursus) {
        super(id, lastName, firstName, birthDate, genre, email);
        this.cursus = cursus;
    }

    public String getCursus() {
        return cursus;
    }

    public void setCursus(String cursus) {
        this.cursus = cursus;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "cursus='" + cursus + '\'' +
                "} " ;
    }
}

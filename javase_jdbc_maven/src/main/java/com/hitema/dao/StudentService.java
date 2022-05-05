package com.hitema.dao;

import com.hitema.modeles.Student;

import java.util.List;

/**
 * défintion des CRUD (Interface)
 */
public interface StudentService {
    List<Student> getAll();
    Student create(Student student);
    Student read(Integer id);
    Student update(Student student);
    Boolean delete(Integer id);
}

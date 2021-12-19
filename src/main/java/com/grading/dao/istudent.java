package com.grading.dao;

import com.grading.model.Grade;
import com.grading.model.Student;

import java.sql.Connection;
import java.util.ArrayList;

public interface istudent {
    boolean create(Object o);
    boolean updateStudent(long id);
    ArrayList<Student> findAllStudent();
    boolean deleteStudent(long id);
    Connection getSQLConnection();

}

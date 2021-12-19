package com.grading.service;

import com.grading.model.Student;

import java.util.ArrayList;

public interface iStudentService {
    boolean createStudentService(); // create
    ArrayList<Student> getStudentsService(); // read
    boolean updateStudentService(); // update
    boolean deleteStudentService(); // delete
    // helper function
    Student getStudentInfo();

}
